package com.example.animals_app.features.sound_loto.presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeMenuContent(onStartClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "jumping_text")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -30f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "🦁🎨🐶", fontSize = 56.sp, modifier = Modifier.padding(bottom = 16.dp))

        Text(
            text = "Animal Sounds",
            fontSize = 44.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFFFF9800),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .offset(y = offsetY.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = onStartClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            shape = RoundedCornerShape(32.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(70.dp)
        ) {
            Text(text = "ИГРАТЬ 🚀", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}