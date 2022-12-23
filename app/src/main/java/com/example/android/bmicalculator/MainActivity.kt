package com.example.android.bmicalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateBMI() }
    }

    private fun calculateBMI() {
        val redWarning = getDrawable(R.drawable.rounded_view_red)
        val yellowWarning = getDrawable(R.drawable.rounded_view_yellow)
        val greenWarning = getDrawable(R.drawable.rounded_view_green)

        val valueInHeightField = binding.heightEditText.text.toString().toDoubleOrNull()
        val valueInWeightField = binding.weightEditText.text.toString().toDoubleOrNull()
        if (valueInHeightField == null || valueInWeightField == null) {
            return
        }
        val result = (valueInWeightField.div((valueInHeightField.times(valueInHeightField)))).toInt()

        if (result < 17) {
            binding.resultWarning.text = getString(R.string.result_severe_underweight, result.toString())
            binding.resultWarning.background = redWarning
        }
        if (result in 17 .. 18) {
            binding.resultWarning.text = getString(R.string.result_underweight, result.toString())
            binding.resultWarning.background = yellowWarning
        }
        if (result in 18 .. 25) {
            binding.resultWarning.text = getString(R.string.result_normal, result.toString())
            binding.resultWarning.background = greenWarning
        }
        if (result in 25..30) {
            binding.resultWarning.text = getString(R.string.result_overweight, result.toString())
            binding.resultWarning.background = yellowWarning
        }
        if (result > 30) {
            binding.resultWarning.text = getString(R.string.result_obesity, result.toString())
            binding.resultWarning.background = redWarning
        }
    }
}