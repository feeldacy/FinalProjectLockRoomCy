package com.example.finalprojectlockroomcy.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectlockroomcy.MainActivity
import com.example.finalprojectlockroomcy.databinding.ActivityLoginPageBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        binding.btnLogin.setOnClickListener{
            // Mengambil input dari ui
            val usernameInput = binding.fieldUsername.text.toString()
            val passwordInput = binding.fieldPassword.text.toString()

            // Mengambil data dari sharedpref
            val savedUsername = sharedPreferences.getString("username", "")
            val savedPassword = sharedPreferences.getString("password", "")

            // Cek input dan data apakah matching
            if (usernameInput == savedUsername && passwordInput == savedPassword){
                val intentToMainActivity = Intent(this, MainActivity::class.java)
                startActivity(intentToMainActivity)
                finish()
            } else {
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }
}