package com.app.android_jetpack_compose_papb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.android_jetpack_compose_papb.ui.theme.AndroidjetpackcomposepapbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidjetpackcomposepapbTheme {
                MainApp()
            }
        }
    }
}

/**
 * Daftar Halaman pada Aplikasi
 */
enum class AppScreen {
    MENU,
    STUDI_KASUS_PROFIL,
    TUGAS_1_COUNTER,
    TUGAS_2_WARNA,
    TUGAS_3_PROFIL
}

/**
 * MainApp: Halaman utama berbasis tombol menu untuk memilih tugas praktikum
 */
@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf(AppScreen.MENU) }

    // Menangani tombol back fisik / gesture perangkat
    BackHandler(enabled = currentScreen != AppScreen.MENU) {
        currentScreen = AppScreen.MENU
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                AppScreen.MENU -> {
                    MainMenuScreen(onNavigate = { screen -> currentScreen = screen })
                }
                AppScreen.STUDI_KASUS_PROFIL -> {
                    TaskScreenContainer(
                        title = "3.5.5 Profil Mahasiswa",
                        onBack = { currentScreen = AppScreen.MENU }
                    ) {
                        ProfileCard()
                    }
                }
                AppScreen.TUGAS_1_COUNTER -> {
                    TaskScreenContainer(
                        title = "Tugas 1: Counter Plus-Minus",
                        onBack = { currentScreen = AppScreen.MENU }
                    ) {
                        CounterPlusMinusApp()
                    }
                }
                AppScreen.TUGAS_2_WARNA -> {
                    TaskScreenContainer(
                        title = "Tugas 2: Toggle Warna Box",
                        onBack = { currentScreen = AppScreen.MENU }
                    ) {
                        ColorToggleBoxApp()
                    }
                }
                AppScreen.TUGAS_3_PROFIL -> {
                    TaskScreenContainer(
                        title = "Tugas 3: Profil Interaktif",
                        onBack = { currentScreen = AppScreen.MENU }
                    ) {
                        InteractiveProfileApp()
                    }
                }
            }
        }
    }
}

/**
 * Halaman Awal dengan tombol-tombol yang mengarah ke masing-masing tugas
 */
@Composable
fun MainMenuScreen(onNavigate: (AppScreen) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Praktikum PAPB - Modul 3",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Konsep State dan Rekomposisi",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Tombol 3.5.5 Profil
        Button(
            onClick = { onNavigate(AppScreen.STUDI_KASUS_PROFIL) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "3.5.5 Profil Mahasiswa (Studi Kasus)", fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tombol Tugas 1
        Button(
            onClick = { onNavigate(AppScreen.TUGAS_1_COUNTER) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Tugas 1: Counter Plus-Minus", fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tombol Tugas 2
        Button(
            onClick = { onNavigate(AppScreen.TUGAS_2_WARNA) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Tugas 2: Toggle Warna Box", fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tombol Tugas 3
        Button(
            onClick = { onNavigate(AppScreen.TUGAS_3_PROFIL) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Tugas 3: Profil Interaktif", fontSize = 15.sp)
        }
    }
}

/**
 * Container untuk menampilkan tugas dengan bilah tombol 'Kembali ke Menu' di bagian atas
 */
@Composable
fun TaskScreenContainer(
    title: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(onClick = onBack) {
                Text("← Kembali ke Menu")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

/**
 * 3.5.5 Studi Kasus Mini (Profil dengan State)
 * Menampilkan foto profil, nama mahasiswa, deskripsi, dan tombol Follow/Unfollow dengan state.
 */
@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    var isFollowed by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Foto Profil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Nama: Rusdi",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Mahasiswa Teknik Informatika")
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { isFollowed = !isFollowed }) {
            Text(if (isFollowed) "Unfollow" else "Follow")
        }
    }
}

/**
 * 3.5.2 CounterApp (dari percobaan sebelumnya)
 */
@Composable
fun CounterApp() {
    var count by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Jumlah Klik: $count")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { count++ }) {
            Text("Tambah")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    AndroidjetpackcomposepapbTheme {
        ProfileCard()
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    AndroidjetpackcomposepapbTheme {
        MainApp()
    }
}

