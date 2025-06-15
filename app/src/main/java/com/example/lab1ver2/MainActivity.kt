package com.example.lab1ver2

import android.os.Bundle
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
import com.example.lab1ver2.ui.theme.Lab1ver2Theme

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.app.Activity
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText1 = findViewById<EditText>(R.id.editText1)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val button = findViewById<Button>(R.id.button)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        button.setOnClickListener {
            try {
                val inputType = editText1.text.toString().trim().lowercase()
                val inputValue = editText2.text.toString().trim().toDouble()

                if (inputValue <= 0) {
                    Toast.makeText(this, "Число должно быть больше нуля!", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                val (k, g, p) = calculateTriangleParameters(inputType, inputValue)

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Введите корректное число!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateTriangleParameters(inputType: String, inputValue: Double): Triple<Double, Double, Double> {
        var k = 0.0  // катет
        var g = 0.0  // гипотенуза
        var p = 0.0  // площадь

        when (inputType) {
            "к" -> {
                k = inputValue
                g = k * sqrt(2.0)
                p = (k * k) / 2
            }
            "г" -> {
                g = inputValue
                k = g / sqrt(2.0)
                p = (k * k) / 2
            }
            "п" -> {
                p = inputValue
                k = sqrt(2 * p)
                g = k * sqrt(2.0)
            }
            else -> throw IllegalArgumentException("Неверный тип параметра")
        }

        return Triple(k, g, p)
    }
}