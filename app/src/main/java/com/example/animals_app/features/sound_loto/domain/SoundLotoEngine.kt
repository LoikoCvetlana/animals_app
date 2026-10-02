package com.example.animals_app.features.sound_loto.domain

import com.example.animals_app.core.data.Card

class SoundLotoEngine(private val allCards: List<Card>) {

    var session = GameSession()
        private set

    var currentRound: Round? = null
        private set

    var currentRoundNumber = 1
        private set

    fun startNewGame() {
        session = GameSession()
        currentRoundNumber = 1
        generateNextRound()
    }

    fun generateNextRound() {
        if (allCards.size < 4) return
        val options = allCards.shuffled().take(4)
        val targetCard = options.random()

        currentRound = Round(options = options, target = targetCard)
    }

    fun checkChildAnswer(selectedCard: Card): Boolean {
        val target = currentRound?.target ?: return false
        val isCorrect = selectedCard == target

        if (isCorrect) {
            session.correctAnswers++
        } else {
            session.wrongAnswers++
        }

        return isCorrect
    }

    fun hasNextRound(): Boolean {
        return currentRoundNumber < session.maxRounds
    }

    fun moveToNextRound() {
        if (hasNextRound()) {
            currentRoundNumber++
            generateNextRound()
        }
    }
}