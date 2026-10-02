package com.example.animals_app.features.sound_loto.presentation

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SoundLotoScreen(viewModel: SoundLotoViewModel) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }

    val playTargetSound = {
        state.targetCard?.soundRes?.let { soundRes ->
            try {
                mediaPlayer.reset()
                val afd = context.resources.openRawResourceFd(soundRes)
                mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                afd.close()
                mediaPlayer.prepare()
                mediaPlayer.setVolume(1.0f, 1.0f)
                mediaPlayer.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    LaunchedEffect(state.currentRoundNumber, state.targetCard, state.isWelcomeScreen) {
        if (!state.isWelcomeScreen && !state.isGameOver) {
            playTargetSound()
        } else if (state.isGameOver && mediaPlayer.isPlaying) {
            mediaPlayer.reset()
        }
    }

    DisposableEffect(Unit) {
        onDispose { mediaPlayer.release() }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE0F7FA),
                        Color(0xFFE8F5E9)
                    )
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isWelcomeScreen -> {
                WelcomeMenuContent(onStartClick = { viewModel.startNewGame() })
            }

            state.isGameOver -> {
                GameOverContent(
                    correct = state.correctAnswers,
                    wrong = state.wrongAnswers,
                    onRestart = { viewModel.startNewGame() }
                )
            }

            else -> {
                GamePlayContent(
                    currentRound = state.currentRoundNumber,
                    maxRounds = state.maxRounds,
                    options = state.options,
                    selectedCardId = state.selectedCardId,
                    targetCardId = state.targetCard?.id,
                    onPlaySoundClick = { playTargetSound() },
                    onCardClick = { card ->
                        mediaPlayer.reset()
                        viewModel.onCardSelected(card)
                    }
                )
            }
        }
    }
}