package com.example.lab1ver2

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.sqrt

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим все элементы интерфейса
        val editText1 = findViewById<EditText>(R.id.editText1)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val button = findViewById<Button>(R.id.button)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Обработка нажатия кнопки
        button.setOnClickListener {
            calculateAndDisplayResult(editText1, editText2, resultTextView)
        }
    }

    private fun calculateAndDisplayResult(
        editText1: EditText,
        editText2: EditText,
        resultTextView: TextView
    ) {
        try {
            // Получаем введённые данные
            val inputType = editText1.text.toString().trim().lowercase()
            val inputValue = editText2.text.toString().trim().toDouble()

            // Проверяем, что число положительное
            if (inputValue <= 0) {
                Toast.makeText(this, "Число должно быть больше нуля!", Toast.LENGTH_SHORT).show()
                return
            }

            // Вычисляем параметры треугольника
            val (k, g, p) = calculateTriangleParameters(inputType, inputValue)

            // Формируем результат
            val resultText = when (inputType) {
                "k" -> "Катет: $inputValue\nГипотенуза: ${"%.2f".format(g)}\nПлощадь: ${"%.2f".format(p)}"
                "q" -> "Гипотенуза: $inputValue\nКатет: ${"%.2f".format(k)}\nПлощадь: ${"%.2f".format(p)}"
                "p" -> "Площадь: $inputValue\nКатет: ${"%.2f".format(k)}\nГипотенуза: ${"%.2f".format(g)}"
                else -> "❌ Ошибка! Введите 'k', 'q' или 'p' в первое поле."
            }

            // Выводим результат
            resultTextView.text = resultText

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Введите число во второе поле!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateTriangleParameters(
        inputType: String,
        inputValue: Double
    ): Triple<Double, Double, Double> {
        var k = 0.0  // катет
        var g = 0.0  // гипотенуза
        var p = 0.0  // площадь

        when (inputType) {
            "k" -> {  // Если дан катет
                k = inputValue
                g = k * sqrt(2.0)          // гипотенуза = катет * √2
                p = (k * k) / 2             // площадь = (катет²) / 2
            }
            "q" -> {  // Если дана гипотенуза
                g = inputValue
                k = g / sqrt(2.0)          // катет = гипотенуза / √2
                p = (k * k) / 2             // площадь = (катет²) / 2
            }
            "p" -> {  // Если дана площадь
                p = inputValue
                k = sqrt(2 * p)             // катет = √(2 * площадь)
                g = k * sqrt(2.0)           // гипотенуза = катет * √2
            }
            else -> throw IllegalArgumentException("Неверный тип параметра")
        }

        return Triple(k, g, p)
    }
}