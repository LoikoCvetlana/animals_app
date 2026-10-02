package com.example.animals_app.features.sound_loto.presentation

import com.example.animals_app.core.data.Card

data class SoundLotoUiState(
    val isWelcomeScreen: Boolean = true,
    val isGameOver: Boolean = false,
    val currentRoundNumber: Int = 1,
    val maxRounds: Int = 5,
    val options: List<Card> = emptyList(),
    val targetCard: Card? = null,
    val correctAnswers: Int = 0,
    val wrongAnswers: Int = 0,
    val selectedCardId: Int? = null,
    val isCheckingAnswer: Boolean = false
)