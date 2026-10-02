package com.app.android_jetpack_compose_papb

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.android_jetpack_compose_papb.ui.theme.AndroidjetpackcomposepapbTheme

/**
 * Tugas 3.7 - No 2: Tombol Toggle Warna
 * - Buat sebuah Box berukuran 200×200 dp.
 * - Klik pertama → warna Merah.
 * - Klik kedua → warna Hijau.
 * - Klik berikutnya → bergantian kembali.
 * - Gunakan state untuk menyimpan warna saat ini.
 */
@Composable
fun ColorToggleBoxApp(modifier: Modifier = Modifier) {

    var isRed by remember { mutableStateOf(true) }

    val currentColor = if (isRed) Color(0xFFE53935) else Color(0xFF43A047)
    val colorLabel = if (isRed) "MERAH" else "HIJAU"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tombol Toggle Warna",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Warna Saat Ini: $colorLabel",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(currentColor)
                .clickable { isRed = !isRed }
                .padding(16.dp)
        ) {
            Text(
                text = "Klik Kotak Ini!\n($colorLabel)",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { isRed = !isRed }) {
            Text(text = "Ubah Warna")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorToggleBoxAppPreview() {
    AndroidjetpackcomposepapbTheme {
        ColorToggleBoxApp()
    }
}
