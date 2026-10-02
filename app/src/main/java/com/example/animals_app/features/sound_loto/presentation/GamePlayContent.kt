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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animals_app.core.data.Card

@Composable
fun GamePlayContent(
    currentRound: Int,
    maxRounds: Int,
    options: List<Card>,
    selectedCardId: Int?,
    targetCardId: Int?,
    onPlaySoundClick: () -> Unit,
    onCardClick: (Card) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Раунд $currentRound из $maxRounds",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF37474F),
            modifier = Modifier.padding(top = 24.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onPlaySoundClick,
            modifier = Modifier.size(150.dp),
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "📢", fontSize = 42.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Повторить", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        AnimalGrid(
            options = options,
            selectedCardId = selectedCardId,
            targetCardId = targetCardId,
            onCardClick = onCardClick
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}