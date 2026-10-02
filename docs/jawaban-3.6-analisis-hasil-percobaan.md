# Data, Analisis, dan Tugas Praktikum (Modul 3)

Dokumen ini berisi jawaban data dan analisis hasil percobaan (3.6) serta penjelasan implementasi tugas praktikum (3.7) untuk **Modul 3: Konsep State dan Rekomposisi** pada mata kuliah Pengembangan Aplikasi Perangkat Bergerak (PAPB).

---

## 1. State Dasar

### a. Apa yang terjadi pada tampilan ketika nilai `count` diubah pada aplikasi Counter?
Ketika nilai `count` diubah, Jetpack Compose secara otomatis memicu proses rekomposisi (*recomposition*) pada fungsi Composable yang membaca state tersebut. Tampilan layar kemudian digambar ulang untuk menampilkan nilai angka terbaru tanpa perlu memuat ulang seluruh halaman.

### b. Mengapa teks pada `Button` dan `Text` bisa langsung berubah tanpa dipanggil ulang secara manual?
Hal ini terjadi karena Jetpack Compose menggunakan paradigma deklaratif berbasis observasi (*snapshot state tracking*). Begitu nilai state yang diobservasi berubah, Compose melacak komponen UI mana saja yang bergantung pada data tersebut dan langsung merender ulang elemen yang relevan tanpa manipulasi manual seperti `setText()`.

---

## 2. Toggle State

### a. Bagaimana Compose mengetahui kapan harus mengganti teks "Follow" menjadi "Unfollow"?
Compose mengetahui perubahan tersebut melalui observasi terhadap state `isFollowed` yang dibungkus `mutableStateOf`. Saat tombol diklik dan nilai `isFollowed` dibalik (`!isFollowed`), Compose mendeteksi perubahan data dan mengevaluasi ulang logika percabangan `if (isFollowed) "Unfollow" else "Follow"` pada siklus rekomposisi.

### b. Apa yang akan terjadi jika variabel `isFollowed` dideklarasikan sebagai `var` biasa (tanpa `remember { mutableStateOf() }`)?
Tampilan UI tidak akan pernah berubah meskipun tombol diklik berulang kali, karena variabel biasa tidak dapat diobservasi oleh Compose sehingga tidak memicu rekomposisi. Selain itu, jika terjadi rekomposisi dari komponen lain, nilai variabel biasa tersebut akan di-reset kembali ke nilai awalnya.

---

## 3. State Hoisting

### a. Jelaskan perbedaan pendekatan tanpa state hoisting dan dengan state hoisting.
Tanpa state hoisting (*stateful*), state disimpan dan dikelola langsung di dalam komponen itu sendiri sehingga komponen berdiri sendiri namun sulit dikontrol dari luar. Sebaliknya, dengan state hoisting (*stateless*), state diangkat ke komponen induk lalu diteruskan ke komponen anak melalui parameter data dan fungsi *callback* event (`onValueChange`).

### b. Mengapa state hoisting membuat kode lebih bersih dan mudah digunakan kembali (*reusable*)?
State hoisting memisahkan antara logika pengelolaan data dengan tampilan visual komponen. Hal ini membuat komponen anak menjadi *stateless*, lebih mudah diuji (*testable*), dapat dipakai ulang di berbagai tempat (*reusable*), dan memastikan data memiliki satu sumber kebenaran (*single source of truth*).

---

## 4. Modifier + State

### a. Bagaimana Modifier (`background`, `clickable`) bekerja bersama state untuk mengubah warna kotak?
`Modifier.clickable` berfungsi menangkap interaksi ketukan pengguna untuk mengubah nilai state warna. Ketika state tersebut bernilai baru, proses rekomposisi berjalan dan `Modifier.background` otomatis dievaluasi ulang dengan warna baru sesuai kondisi state terkini.

### b. Apakah UI tetap responsif jika Modifier hanya digunakan tanpa state?
Tidak responsif terhadap perubahan data dinamis. Meskipun event klik pada `clickable` tetap berjalan, tanpa adanya state yang diobservasi, Compose tidak akan menjalankan rekomposisi sehingga tampilan visual pada `Modifier.background` akan tetap statis.

---

## 5. Studi Kasus Profil

### a. Bagaimana tombol Follow/Unfollow di dalam `ProfileCard` memanfaatkan state untuk mengubah teks?
Tombol memanfaatkan state boolean `isFollowed` yang diinisialisasi menggunakan `remember { mutableStateOf(false) }`. Saat tombol ditekan, event `onClick` membalikkan nilai boolean tersebut, sehingga Compose merekomposisi tombol dan mengubah teks labelnya sesuai kondisi terbaru.

### b. Jika aplikasi ditutup dan dibuka lagi, apakah state masih tersimpan? Mengapa demikian?
Tidak tersimpan, nilai state akan kembali ke nilai awal (`false`). Hal ini karena fungsi `remember` hanya menyimpan data di memori selama siklus hidup Composable berjalan; untuk mempertahankan state saat aplikasi ditutup atau orientasi layar diputar, diperlukan `rememberSaveable` atau penyimpanan permanen seperti DataStore/Database.

---

## 6. Penjelasan Implementasi Tugas Praktikum (Modul 3.7)

### Tugas 1: Aplikasi Counter Plus-Minus
* **Komponen Composable:** `CounterPlusMinusApp()`
* **File:** `app/src/main/java/com/app/android_jetpack_compose_papb/Tugas1Counter.kt`
* **Penjelasan Singkat:**
  Menggunakan state integer `var count by remember { mutableStateOf(0) }`. Tombol Tambah (`+`) menambahkan nilai `count` setiap kali diklik. Tombol Kurang (`–`) memiliki validasi `if (count > 0) count--` serta properti `enabled = count > 0`, sehingga tombol otomatis nonaktif saat counter bernilai 0 dan nilai tidak akan pernah turun di bawah nol.

### Tugas 2: Tombol Toggle Warna
* **Komponen Composable:** `ColorToggleBoxApp()`
* **File:** `app/src/main/java/com/app/android_jetpack_compose_papb/Tugas2ColorToggle.kt`
* **Penjelasan Singkat:**
  Menggunakan state boolean `var isRed by remember { mutableStateOf(true) }` untuk menentukan warna kotak aktif (Merah `#E53935` atau Hijau `#43A047`). Komponen `Box` berukuran `200.dp x 200.dp` memanfaatkan `Modifier.clickable` untuk membalikkan state `isRed` bergantian antara merah dan hijau pada setiap klik.

### Tugas 3: Aplikasi Profil Interaktif
* **Komponen Composable:** `InteractiveProfileApp()`
* **File:** `app/src/main/java/com/app/android_jetpack_compose_papb/Tugas3InteractiveProfile.kt`
* **Penjelasan Singkat:**
  Menampilkan foto profil dummy lingkaran (`R.drawable.profile`), nama mahasiswa, dan bio singkat. Menggunakan state boolean `var isFollowed by remember { mutableStateOf(false) }` untuk mengontrol tombol *Follow/Unfollow* serta indikator teks dinamis di bawah tombol: menampilkan *"Anda mengikuti akun ini"* jika bernilai `true`, dan *"Anda belum mengikuti akun ini"* jika bernilai `false`.

