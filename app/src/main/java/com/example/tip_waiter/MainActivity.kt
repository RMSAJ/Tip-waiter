package com.example.tip_waiter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.tip_waiter.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    // calling the binding
    // needs to be revised immediately and know what binding is
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.CalculateButton.setOnClickListener { calculateTip() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)}

    }

    fun calculateTip() {
        val inputNumberByUi = binding.costOfServiceEditText.text.toString().toDouble()

        val amountOftipChosen = if (binding.AmazingButton.isChecked ) {inputNumberByUi * 1.2}
        else if (binding.GoodButton.isChecked) { inputNumberByUi * 1.18}
        else inputNumberByUi
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            amountOftipChosen.roundToInt()
        }
       val formattedAmount =  NumberFormat.getCurrencyInstance().format(amountOftipChosen)
        binding.tipResult.text = getString(R.string.tip_amount, formattedAmount)
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}