package com.example.animals_app.features.sound_loto.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameOverContent(
    correct: Int,
    wrong: Int,
    onRestart: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "🏁🏆🎉", fontSize = 56.sp, modifier = Modifier.padding(bottom = 16.dp))

        Text(
            text = "Игра окончена!",
            fontSize = 36.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFF37474F),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Правильно: $correct ✨", fontSize = 22.sp, color = Color(0xFF4CAF50), fontWeight = FontWeight.Bold)
        Text(text = "Ошибки: $wrong ❌", fontSize = 22.sp, color = Color(0xFFE53935), modifier = Modifier.padding(top = 8.dp))

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onRestart,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(65.dp)
        ) {
            Text(text = "ЕЩЁ РАЗ 🔄", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}