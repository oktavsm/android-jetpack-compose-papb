package com.app.android_jetpack_compose_papb

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.android_jetpack_compose_papb.ui.theme.AndroidjetpackcomposepapbTheme

/**
 * Tugas 3.7 - No 1: Aplikasi Counter Plus-Minus
 * - Buat aplikasi dengan dua tombol: Tambah (+) dan Kurang (–).
 * - Gunakan state (remember { mutableStateOf() }) untuk menyimpan nilai counter.
 * - Pastikan nilai counter tidak bisa turun di bawah nol.
 */
@Composable
fun CounterPlusMinusApp(modifier: Modifier = Modifier) {
    var count by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Aplikasi Counter Plus-Minus",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nilai Counter: $count",
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (count > 0) count--
                },
                enabled = count > 0
            ) {
                Text(text = "– Kurang", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { count++ }) {
                Text(text = "+ Tambah", fontSize = 16.sp)
            }
        }

        if (count == 0) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "* Nilai counter tidak bisa turun di bawah 0",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterPlusMinusAppPreview() {
    AndroidjetpackcomposepapbTheme {
        CounterPlusMinusApp()
    }
}
