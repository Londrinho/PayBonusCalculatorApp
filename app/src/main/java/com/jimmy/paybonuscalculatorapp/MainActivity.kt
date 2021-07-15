package com.jimmy.paybonuscalculatorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jimmy.paybonuscalculatorapp.databinding.ActivityMainBinding
import java.text.NumberFormat
/**
 * This activity allows the user to calculate a bonus given at work
 * based meeting set objectives.
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateBonus() }
    }
    /**
     * This function calculates the bonus that given by a manager
     * and displays it on the screen.
     */
    private fun calculateBonus(){
        // The users monthly salary is captured here
        val stringInTextField = binding.monthlySalaryAfterTax.text.toString()
        //Here ensures that if nothing is written and calculate is pressed, the app doesn't crash
        val salary = stringInTextField.toDoubleOrNull()
        if (salary == null) {
            binding.bonusResult.text = ""
            return
        }

        //This captures the percentage chosen by the user
        val bonusPercentage = when (binding.bonusOptions.checkedRadioButtonId) {
            R.id.option_five_percent -> 0.05
            R.id.option_ten_percent -> 0.10
            R.id.option_fifteen_percent -> 0.15
            else -> 0.05
        }

        //This bonus is calculated, formatted and displayed
        var bonus = bonusPercentage * salary
        val formattedBonus = NumberFormat.getCurrencyInstance().format(bonus)
        binding.bonusResult.text = getString(R.string.bonus_amount, formattedBonus)

    }
}