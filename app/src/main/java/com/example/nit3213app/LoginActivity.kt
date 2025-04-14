package com.example.nit3213app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nit3213app.api.ApiClient
import com.example.nit3213app.api.LoginRequest
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // connects layout buttons and fields to kotlin code
        val usernameInput = findViewById<TextInputEditText>(R.id.etUsername)
        val passwordInput = findViewById<TextInputEditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        // When Login button is clicked
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // Check if both fields are filled
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            } else {
                progressBar.visibility = View.VISIBLE

                // Coroutine (background thread)
                lifecycleScope.launch {
                    try {
                        val response = ApiClient.apiService.login(LoginRequest(username, password))
                        progressBar.visibility = View.GONE

                        if (response.isSuccessful && response.body() != null) {
                            val keypass = response.body()!!.keypass

                            Toast.makeText(this@LoginActivity, "Keypass: $keypass", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            intent.putExtra("keypass", keypass)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
