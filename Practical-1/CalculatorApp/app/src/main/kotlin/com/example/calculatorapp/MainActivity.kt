package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Current number being entered
    private var currentInput = ""
    // The accumulated result so far
    private var result = 0.0
    // The pending operator (+, -, *, /)
    private var pendingOperator = ""
    // Whether the next digit press should clear the display
    private var shouldResetDisplay = false
    // Whether the last result is in error state
    private var isError = false

    private lateinit var tvDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        // Restore state
        if (savedInstanceState != null) {
            currentInput = savedInstanceState.getString(KEY_CURRENT_INPUT, "")
            result = savedInstanceState.getDouble(KEY_RESULT, 0.0)
            pendingOperator = savedInstanceState.getString(KEY_PENDING_OPERATOR, "")
            shouldResetDisplay = savedInstanceState.getBoolean(KEY_SHOULD_RESET, false)
            isError = savedInstanceState.getBoolean(KEY_IS_ERROR, false)
            val displayText = savedInstanceState.getString(KEY_DISPLAY_TEXT, "0")
            tvDisplay.text = displayText
        } else {
            tvDisplay.text = "0"
        }

        // Digit buttons
        val digitIds = mapOf(
            R.id.btn0 to "0",
            R.id.btn1 to "1",
            R.id.btn2 to "2",
            R.id.btn3 to "3",
            R.id.btn4 to "4",
            R.id.btn5 to "5",
            R.id.btn6 to "6",
            R.id.btn7 to "7",
            R.id.btn8 to "8",
            R.id.btn9 to "9"
        )
        for ((id, digit) in digitIds) {
            findViewById<Button>(id).setOnClickListener { onDigit(digit) }
        }

        // Decimal point
        findViewById<Button>(R.id.btnDot).setOnClickListener { onDecimal() }

        // Operator buttons
        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperator("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { onOperator("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { onOperator("×") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { onOperator("÷") }

        // Equals
        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEquals() }

        // Clear
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClear() }

        // Backspace
        findViewById<Button>(R.id.btnBackspace).setOnClickListener { onBackspace() }

        // Toggle sign
        findViewById<Button>(R.id.btnToggleSign).setOnClickListener { onToggleSign() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_CURRENT_INPUT, currentInput)
        outState.putDouble(KEY_RESULT, result)
        outState.putString(KEY_PENDING_OPERATOR, pendingOperator)
        outState.putBoolean(KEY_SHOULD_RESET, shouldResetDisplay)
        outState.putBoolean(KEY_IS_ERROR, isError)
        outState.putString(KEY_DISPLAY_TEXT, tvDisplay.text.toString())
    }

    private fun onDigit(digit: String) {
        if (isError) return
        if (shouldResetDisplay) {
            currentInput = ""
            shouldResetDisplay = false
        }
        // Avoid leading zeros (except "0.")
        if (currentInput == "0" && digit != ".") {
            currentInput = digit
        } else {
            currentInput += digit
        }
        tvDisplay.text = currentInput
    }

    private fun onDecimal() {
        if (isError) return
        if (shouldResetDisplay) {
            currentInput = "0"
            shouldResetDisplay = false
        }
        if (!currentInput.contains(".")) {
            currentInput = if (currentInput.isEmpty()) "0." else "$currentInput."
            tvDisplay.text = currentInput
        }
    }

    private fun onOperator(op: String) {
        if (isError) return
        // Apply any pending operation first
        applyPendingOperator()
        pendingOperator = op
        shouldResetDisplay = true
    }

    private fun onEquals() {
        if (isError) return
        applyPendingOperator()
        pendingOperator = ""
        shouldResetDisplay = true
    }

    private fun applyPendingOperator() {
        val inputValue = currentInput.toDoubleOrNull() ?: result
        if (pendingOperator.isEmpty()) {
            // No pending operator — just capture the current input as the running result
            result = inputValue
        } else {
            val newResult = when (pendingOperator) {
                "+" -> result + inputValue
                "-" -> result - inputValue
                "×" -> result * inputValue
                "÷" -> {
                    if (inputValue == 0.0) {
                        showError()
                        return
                    }
                    result / inputValue
                }
                else -> inputValue
            }
            result = newResult
        }
        tvDisplay.text = formatResult(result)
        currentInput = formatResult(result)
    }

    private fun showError() {
        tvDisplay.text = getString(R.string.error_divide_by_zero)
        isError = true
        result = 0.0
        pendingOperator = ""
        currentInput = ""
        shouldResetDisplay = false
    }

    private fun onClear() {
        currentInput = ""
        result = 0.0
        pendingOperator = ""
        shouldResetDisplay = false
        isError = false
        tvDisplay.text = "0"
    }

    private fun onBackspace() {
        if (isError || shouldResetDisplay) return
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            tvDisplay.text = if (currentInput.isEmpty()) "0" else currentInput
        }
    }

    private fun onToggleSign() {
        if (isError) return
        if (shouldResetDisplay) return
        val value = currentInput.toDoubleOrNull() ?: return
        val toggled = -value
        currentInput = formatResult(toggled)
        tvDisplay.text = currentInput
    }

    /** Format a Double result: strip trailing ".0" for whole numbers. */
    private fun formatResult(value: Double): String {
        return if (value == value.toLong().toDouble()) {
            value.toLong().toString()
        } else {
            value.toBigDecimal().stripTrailingZeros().toPlainString()
        }
    }

    companion object {
        private const val KEY_CURRENT_INPUT = "currentInput"
        private const val KEY_RESULT = "result"
        private const val KEY_PENDING_OPERATOR = "pendingOperator"
        private const val KEY_SHOULD_RESET = "shouldResetDisplay"
        private const val KEY_IS_ERROR = "isError"
        private const val KEY_DISPLAY_TEXT = "displayText"
    }
}
