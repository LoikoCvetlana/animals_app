package com.example.animals_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.animals_app.core.data.LocalAnimalRepository
import com.example.animals_app.features.sound_loto.domain.SoundLotoEngine
import com.example.animals_app.features.sound_loto.presentation.SoundLotoScreen
import com.example.animals_app.features.sound_loto.presentation.SoundLotoViewModel
import com.example.animals_app.ui.theme.Animals_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = LocalAnimalRepository()
        val lotoViewModel = SoundLotoViewModel(repository = repository, context = this)
        setContent {
            SoundLotoScreen(viewModel = lotoViewModel)
        }
    }
}