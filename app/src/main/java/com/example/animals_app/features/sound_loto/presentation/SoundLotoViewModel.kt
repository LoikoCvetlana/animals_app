package com.example.animals_app.features.sound_loto.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animals_app.core.data.AnimalRepository
import com.example.animals_app.core.data.Card
import com.example.animals_app.features.sound_loto.domain.SoundLotoEngine
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SoundLotoViewModel(
    private val repository: AnimalRepository,
    context: Context
) : ViewModel() {

    private val allAnimalCards: List<Card> = repository.getAllAnimals(context)
    private val engine = SoundLotoEngine(allCards = allAnimalCards)
    private val _uiState = MutableStateFlow(SoundLotoUiState())
    val uiState: StateFlow<SoundLotoUiState> = _uiState.asStateFlow()

    private fun updateUiState() {
        _uiState.update { currentState ->
            currentState.copy(
                currentRoundNumber = engine.currentRoundNumber,
                maxRounds = engine.session.maxRounds,
                options = engine.currentRound?.options ?: emptyList(),
                targetCard = engine.currentRound?.target,
                correctAnswers = engine.session.correctAnswers,
                wrongAnswers = engine.session.wrongAnswers,
                selectedCardId = currentState.selectedCardId,
                isCheckingAnswer = currentState.isCheckingAnswer
            )
        }
    }

    fun startNewGame() {
        engine.startNewGame()
        _uiState.update { currentState ->
            currentState.copy(
                isWelcomeScreen = false,
                isGameOver = false,
                selectedCardId = null,
                isCheckingAnswer = false
            )
        }
        updateUiState()
    }

    fun onCardSelected(selectedCard: Card) {
        if (_uiState.value.isCheckingAnswer) return

        _uiState.update { currentState ->
            currentState.copy(
                selectedCardId = selectedCard.id,
                isCheckingAnswer = true
            )
        }

        engine.checkChildAnswer(selectedCard)

        viewModelScope.launch {
            delay(1500)

            if (engine.hasNextRound()) {
                engine.moveToNextRound()

                _uiState.update { currentState ->
                    currentState.copy(
                        selectedCardId = null,
                        isCheckingAnswer = false
                    )
                }
                updateUiState()
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        correctAnswers = engine.session.correctAnswers,
                        wrongAnswers = engine.session.wrongAnswers,
                        isGameOver = true,
                        selectedCardId = null,
                        isCheckingAnswer = false
                    )
                }
            }
        }
    }
    init {
        if (allAnimalCards.size >= 4) {
            engine.startNewGame()
            updateUiState()
        }
    }
}