package com.app.android_jetpack_compose_papb

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.android_jetpack_compose_papb.ui.theme.AndroidjetpackcomposepapbTheme

/**
 * Tugas 3.7 - No 3: Aplikasi Profil Interaktif
 * - Tampilkan foto profil (dummy), nama, dan deskripsi singkat.
 * - Tambahkan tombol Follow/Unfollow yang berubah sesuai state.
 * - Tambahkan indikator teks di bawah tombol:
 *   * Jika diikuti -> tampilkan “Anda mengikuti akun ini”.
 *   * Jika tidak diikuti -> tampilkan “Anda belum mengikuti akun ini”.
 */
@Composable
fun InteractiveProfileApp(modifier: Modifier = Modifier) {
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

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Nama: Rusdi",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Mahasiswa Teknik Informatika",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { isFollowed = !isFollowed },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFollowed) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = if (isFollowed) "Unfollow" else "Follow",
                fontSize = 15.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = if (isFollowed) "Anda mengikuti akun ini" else "Anda belum mengikuti akun ini",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = if (isFollowed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InteractiveProfileAppPreview() {
    AndroidjetpackcomposepapbTheme {
        InteractiveProfileApp()
    }
}
