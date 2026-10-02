package com.example.animals_app.features.sound_loto.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animals_app.core.data.Card

@Composable
fun AnimalGrid(
    options: List<Card>,
    selectedCardId: Int?,
    targetCardId: Int?,
    onCardClick: (Card) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        items(options) { animalCard ->
            val isSelected = animalCard.id == selectedCardId
            val isTarget = animalCard.id == targetCardId
            val shouldHighlight = isSelected || (selectedCardId != null && isTarget)

            AnimalCard(
                animal = animalCard,
                isHighlighted = shouldHighlight,
                isCorrect = isTarget,
                onCardClick = { onCardClick(animalCard) }
            )
        }
    }
}