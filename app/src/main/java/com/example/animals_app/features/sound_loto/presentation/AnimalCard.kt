package com.example.animals_app.features.sound_loto.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.animals_app.core.data.Card

@Composable
fun AnimalCard(
    animal: Card,
    isHighlighted: Boolean,
    isCorrect: Boolean,
    onCardClick: () -> Unit
) {

    val targetBorderColor = when {
        !isHighlighted -> Color.Transparent
        isCorrect -> Color(0xFF4CAF50)
        else -> Color(0xFFE53935)
    }

    val borderColor by animateColorAsState(
        targetValue = targetBorderColor,
        animationSpec = tween(durationMillis = 300),
        label = "border_color"
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .border(
                width = if (isHighlighted) 6.dp else 0.dp, // Рисуем жирную рамку 6dp только при проверке
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onCardClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image(
            painter = painterResource(id = animal.imageRes),
            contentDescription = animal.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}