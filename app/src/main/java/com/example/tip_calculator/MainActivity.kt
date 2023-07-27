package com.example.tip_calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tip_calculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringServiceCost = binding.costOfServiceEditText.text.toString()
        val cost = stringServiceCost.toDoubleOrNull()
        if (cost == null) {
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_percent_tip -> 0.2
            R.id.eighteen_percent_tip -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        if (binding.roundUpSwitch.isChecked) tip = kotlin.math.ceil(tip)
        displayTip(tip)

    }

    private fun displayTip(tip : Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}