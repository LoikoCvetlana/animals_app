package com.example.animals_app.core.data
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.animals_app.R

interface AnimalRepository {

    fun getAllAnimals(context: Context): List<Card>
}

class LocalAnimalRepository : AnimalRepository {

    @SuppressLint("DiscouragedApi")
    override fun getAllAnimals(context: Context): List<Card> {
        val animalList = mutableListOf<Card>()
        val rawArray = context.resources.getStringArray(R.array.animal_database)

        for ((index, item) in rawArray.withIndex()) {
            if (item.isNullOrBlank()) {
                Log.w("AnimalRepository", "Предупреждение: Строка под индексом $index пустая. Пропускаем.")
                continue
            }

            val tokens = item.split("|")

            if (tokens.size != 3) {
                Log.e("AnimalRepository", "Ошибка парсинга: Строка '$item' имеет неверный формат (должно быть 3 элемента). Пропускаем.")
                continue
            }

            val name = tokens[0]
            val soundName = tokens[1]
            val imageName = tokens[2]

            if (name.isBlank() || soundName.isBlank() || imageName.isBlank()) {
                Log.e("AnimalRepository", "Ошибка: Одно из полей для животного '$item' пустое. Пропускаем.")
                continue
            }


            val soundResId = context.resources.getIdentifier(soundName, "raw", context.packageName)
            val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)

            if (soundResId == 0 || imageResId == 0) {
                if (soundResId == 0) {
                    Log.e("AnimalRepository", "Lock ❌: У животного '$name' не подгружается ЗВУК (файл '$soundName' не найден в res/raw).")
                }
                if (imageResId == 0) {
                    Log.e("AnimalRepository", "Lock ❌: У животного '$name' не подгружается КАРТИНКА (файл '$imageName' не найден в res/drawable).")
                }
                continue
            }

            val card = Card(
                id = index + 1,
                name = name,
                soundRes = soundResId,
                imageRes = imageResId
            )
            animalList.add(card)
        }

        return animalList
    }
}