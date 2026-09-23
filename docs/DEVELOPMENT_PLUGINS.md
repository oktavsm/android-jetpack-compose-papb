# Panduan Plugin Pendukung Development Android & Jetpack Compose

Dokumentasi ini merangkum plugin-plugin pendukung produktivitas dan pengembangan Jetpack Compose yang telah dipasang pada IDE Android Studio.

> [!NOTE]
> Seluruh plugin ini dipasang pada **level IDE Android Studio** (`~/.local/share/Google/AndroidStudio2026.1.3/`).
> Artinya, plugin-plugin ini **otomatis aktif secara global di semua branch** (`main`, `module/*`, dll.) serta di seluruh project Android lainnya di mesin ini tanpa perlu instalasi ulang.

---

## 1. Daftar Plugin yang Terpasang & Cara Penggunaannya

### A. Compose HotSwan v2 (oleh Jaewoong Eum / skydoves)
Plugin *hot reload* generasi terbaru khusus untuk Jetpack Compose. Menggunakan *custom interpreter engine* yang memungkinkan perubahan kode UI (termasuk penambahan/pengurangan composable dan layout) langsung terlihat di layar emulator atau perangkat fisik dalam hitungan detik tanpa perlu melakukan *rebuild* APK atau restart aplikasi, sambil tetap mempertahankan state layar saat ini (*state preservation*).

- **Tombol & Toolbar**:
  - Ikon **HotSwan** berada di toolbar atas (sejajar dengan tombol *Run* / *Debug*).
- **Shortcut Keyboard**:
  - **`Ctrl + Shift + F10`**: Memulai atau menghentikan (*toggle*) sesi HotSwan Hot Reload.
  - **`Ctrl + Shift + F11`**: *Manual Hot Reload* (memaksa kompilasi cepat dan mengirim *bytecode* perubahan ke aplikasi yang sedang berjalan).
- **Tool Window**:
  - Klik tab **HotSwan** di sidebar sebelah kanan untuk melihat log real-time, status koneksi device, dan diagnostic reload.
- **Konfigurasi**:
  - `File` > `Settings` > `Tools` > `Compose HotSwan`.

---

### B. Compose Navigation Graph (oleh Jaewoong Eum / skydoves)
Plugin visualisasi graf navigasi untuk Jetpack Compose. Menghadirkan kembali kemudahan visual editor navigasi (seperti era XML Navigation Editor) ke dalam era Compose yang berbasis kode murni.

- **Fungsi Utama**:
  - Memetakan alur navigasi antar-halaman (*screens / destinations*) secara visual.
  - Menampilkan thumbnail `@Preview` composable langsung di setiap node halaman.
  - Menampilkan arah panah rute navigasi dan argumen typed navigation.
- **Tool Window**:
  - Buka tab **NavGraph Graph** di sidebar sebelah kanan Android Studio untuk melihat diagram alur navigasi.
  - Mendukung fitur export diagram navigasi ke format **PNG** atau **HTML**.
- **Konfigurasi**:
  - `File` > `Settings` > `Tools` > `NavGraph Graph`.

---

### C. Rainbow Brackets
Plugin pewarna kurung bersarang (*nested brackets*). Sangat krusial dalam pengembangan Jetpack Compose karena struktur hirarki UI berbasis fungsi bersarang (`Column { Card { Row { Box { ... } } } }`).

- **Fungsi Utama**:
  - Memberikan warna pelangi berbeda untuk setiap pasang kurung `()`, `{}`, dan `[]` sesuai kedalaman tingkatannya.
- **Cara Penggunaan**:
  - Berjalan otomatis secara langsung di editor kode Kotlin.
  - **Highlight Scope**: Tekan `Ctrl + Klik Tengah` (atau `Alt + Klik Kanan`) pada sebuah kurung untuk menyorot (*highlight*) seluruh blok yang dilingkupi kurung tersebut.

---

### D. Key Promoter X
Asisten produktivitas untuk membantu menghafal shortcut keyboard Android Studio.

- **Fungsi Utama**:
  - Setiap kali Anda mengklik tombol menu atau aksi menggunakan mouse yang sebenarnya memiliki shortcut keyboard, Key Promoter X akan menampilkan balon notifikasi shortcut tersebut di sudut kanan bawah layar.
- **Tool Window**:
  - Klik tab **Key Promoter X** di sidebar sebelah kanan untuk melihat statistik tombol mouse yang paling sering Anda klik beserta shortcut alternatifnya.

---

### E. JSON To Kotlin Class (JsonToKotlinClass)
Plugin generator data class Kotlin instan dari teks JSON mentah. Sangat memudahkan saat mengintegrasikan REST API atau membuat model data tiruan untuk tugas kuliah.

- **Cara Penggunaan**:
  - **Shortcut**: Tekan **`Alt + K`** di dalam editor/package yang diinginkan.
  - **Lewat Menu**: Klik kanan pada package tujuan (misal di folder `model` atau `data`) > pilih **New** > **Kotlin Data Class File from JSON**.
  - Tempelkan (*paste*) JSON pada dialog, masukkan nama class utama, lalu klik **Generate**. Plugin akan otomatis membuat seluruh `data class` beserta anotasi serialisasi.

---

## 2. Ringkasan Tombol & Shortcut Penting

| Plugin | Tombol / Letak Menu | Shortcut Keyboard | Fungsi Utama |
| :--- | :--- | :--- | :--- |
| **Compose HotSwan** | Toolbar Atas (Ikon HotSwan) | `Ctrl + Shift + F10` | Start / Stop sesi Hot Reload |
| **Compose HotSwan** | Toolbar Atas | `Ctrl + Shift + F11` | Force Hot Reload ke perangkat |
| **NavGraph Graph** | Sidebar Kanan (`NavGraph Graph`) | - | Melihat visual peta rute Compose |
| **Rainbow Brackets** | Editor Kode | `Ctrl + Klik Tengah` / `Alt + Klik Kanan` | Highlight scope blok kurung |
| **Key Promoter X** | Sidebar Kanan (`Key Promoter X`) | Otomatis muncul saat klik mouse | Notifikasi & statistik shortcut |
| **JSON To Kotlin** | Klik kanan package > New > Kotlin Data Class from JSON | `Alt + K` | Generate model Kotlin dari JSON |

---

## 3. Langkah yang Perlu Anda Lakukan (Setup Sendiri)

1. **Restart Android Studio**:
   - Jika Android Studio saat ini sedang dalam kondisi terbuka, silakan tutup (*Quit*) dan buka kembali.
   - Saat Android Studio dibuka kembali, seluruh plugin baru akan diinisialisasi dan tab toolbar serta sidebar kanan akan langsung tersedia.
2. **Memulai HotSwan pada Project**:
   - Jalankan (*Run* atau *Debug*) aplikasi ke Emulator / HP seperti biasa.
   - Tekan **`Ctrl + Shift + F10`** atau klik ikon HotSwan di toolbar atas untuk mengaktifkan sesi Hot Reload.
   - Ubah kode Compose Anda (misal ubah teks, warna, padding), simpan file (`Ctrl + S`), dan perubahan akan langsung terefleksi di layar!
