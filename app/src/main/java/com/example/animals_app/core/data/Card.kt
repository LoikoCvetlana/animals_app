package com.example.animals_app.core.data

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

@SuppressLint("SupportAnnotationUsage")
data class Card(
    val id: Int,
    val name: String,
    @RawRes val soundRes: Int,
    @DrawableRes val imageRes: Int
)