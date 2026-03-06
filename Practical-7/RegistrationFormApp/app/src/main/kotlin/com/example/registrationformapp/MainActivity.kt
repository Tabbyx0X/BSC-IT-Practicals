package com.example.registrationformapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * MainActivity
 *
 * Displays a registration form with:
 *  - Full Name field
 *  - Email field
 *  - Password field (masked input)
 *  - Confirm Password field (masked input)
 *  - Register button
 *
 * Validation rules:
 *  - No field may be empty
 *  - Email must contain '@'
 *  - Password and Confirm Password must match
 *  - On success, a Toast "Registration successful" is shown
 */
class MainActivity : AppCompatActivity() {

    // View references
    private lateinit var fullNameLayout: TextInputLayout
    private lateinit var emailLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var confirmPasswordLayout: TextInputLayout
    private lateinit var etFullName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind views from layout
        fullNameLayout        = findViewById(R.id.fullNameLayout)
        emailLayout           = findViewById(R.id.emailLayout)
        passwordLayout        = findViewById(R.id.passwordLayout)
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout)
        etFullName            = findViewById(R.id.etFullName)
        etEmail               = findViewById(R.id.etEmail)
        etPassword            = findViewById(R.id.etPassword)
        etConfirmPassword     = findViewById(R.id.etConfirmPassword)
        btnRegister           = findViewById(R.id.btnRegister)

        // Set click listener on the Register button
        btnRegister.setOnClickListener {
            handleRegistration()
        }
    }

    /**
     * Validates all input fields and shows appropriate feedback.
     *
     * Validation checks (in order):
     *  1. Full name must not be empty.
     *  2. Email must not be empty and must contain '@'.
     *  3. Password must not be empty.
     *  4. Confirm password must not be empty.
     *  5. Password and confirm password must match.
     *
     * If all validations pass, shows a "Registration successful" Toast.
     */
    private fun handleRegistration() {
        val fullName        = etFullName.text?.toString()?.trim() ?: ""
        val email           = etEmail.text?.toString()?.trim() ?: ""
        // Passwords are not trimmed — leading/trailing spaces may be intentional
        val password        = etPassword.text?.toString() ?: ""
        val confirmPassword = etConfirmPassword.text?.toString() ?: ""

        // Clear any previous errors
        fullNameLayout.error        = null
        emailLayout.error           = null
        passwordLayout.error        = null
        confirmPasswordLayout.error = null

        var isValid = true

        // 1. Validate full name — must not be empty
        if (fullName.isEmpty()) {
            fullNameLayout.error = getString(R.string.error_name_empty)
            isValid = false
        }

        // 2. Validate email — must not be empty and must contain '@'
        if (email.isEmpty()) {
            emailLayout.error = getString(R.string.error_email_empty)
            isValid = false
        } else if (!email.contains('@')) {
            emailLayout.error = getString(R.string.error_email_invalid)
            isValid = false
        }

        // 3. Validate password — must not be empty
        if (password.isEmpty()) {
            passwordLayout.error = getString(R.string.error_password_empty)
            isValid = false
        }

        // 4. Validate confirm password — must not be empty
        if (confirmPassword.isEmpty()) {
            confirmPasswordLayout.error = getString(R.string.error_confirm_password_empty)
            isValid = false
        }

        // 5. Passwords must match (only checked when both are non-empty)
        if (password.isNotEmpty() && confirmPassword.isNotEmpty() && password != confirmPassword) {
            confirmPasswordLayout.error = getString(R.string.error_passwords_mismatch)
            isValid = false
        }

        // If all fields are valid, show a success message
        if (isValid) {
            Toast.makeText(this, getString(R.string.toast_registration_success), Toast.LENGTH_SHORT).show()
        }
    }
}
