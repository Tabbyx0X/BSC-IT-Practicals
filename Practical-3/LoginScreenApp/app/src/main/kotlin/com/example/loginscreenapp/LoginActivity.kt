package com.example.loginscreenapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * LoginActivity
 *
 * Displays a simple login screen with:
 *  - A username field
 *  - A password field (masked input)
 *  - A login button
 *
 * Validation rules:
 *  - Username must not be empty
 *  - Password must not be empty
 *  - If both are filled, a Toast is shown: "Login successful"
 */
class LoginActivity : AppCompatActivity() {

    // View references
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Bind views from layout
        usernameLayout = findViewById(R.id.usernameLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        etUsername     = findViewById(R.id.etUsername)
        etPassword     = findViewById(R.id.etPassword)
        btnLogin       = findViewById(R.id.btnLogin)

        // Set click listener on the Login button
        btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    /**
     * Validates the input fields and shows the appropriate feedback.
     * - Shows an error on the field if it is empty.
     * - Shows a "Login successful" Toast if both fields are filled.
     */
    private fun handleLogin() {
        val username = etUsername.text?.toString()?.trim() ?: ""
        // Passwords are not trimmed — leading/trailing spaces may be intentional
        val password = etPassword.text?.toString() ?: ""

        // Clear any previous errors
        usernameLayout.error = null
        passwordLayout.error = null

        var isValid = true

        // Validate username — must not be empty
        if (username.isEmpty()) {
            usernameLayout.error = getString(R.string.error_username_empty)
            isValid = false
        }

        // Validate password — must not be empty
        if (password.isEmpty()) {
            passwordLayout.error = getString(R.string.error_password_empty)
            isValid = false
        }

        // If both fields are valid, show a success message
        if (isValid) {
            Toast.makeText(this, getString(R.string.toast_login_success), Toast.LENGTH_SHORT).show()
        }
    }
}
