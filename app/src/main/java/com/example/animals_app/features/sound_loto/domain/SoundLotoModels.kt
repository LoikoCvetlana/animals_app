package com.example.animals_app.features.sound_loto.domain

import com.example.animals_app.core.data.Card

data class  Round(
    val options: List<Card>,
    val target: Card
)

data class GameSession(
    var correctAnswers: Int = 0,
    var wrongAnswers: Int = 0,
    val maxRounds: Int = 5
)