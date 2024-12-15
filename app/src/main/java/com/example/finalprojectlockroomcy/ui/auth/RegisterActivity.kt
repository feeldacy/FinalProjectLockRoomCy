package com.example.finalprojectlockroomcy.ui.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.finalprojectlockroomcy.MainActivity
import com.example.finalprojectlockroomcy.databinding.ActivityRegisterPageBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPageBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)

        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        val username = sharedPreferences.getString("username", null)

        if (isLoggedIn && username != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            // ambil data dari ui
            val usernameInput = binding.fieldUsername.text.toString()
            val emailInput = binding.fieldEmail.text.toString()
            val phoneInput = binding.fieldPhone.text.toString()
            val passwordInput = binding.fieldPassword.text.toString()
            val confirmPasswordInput = binding.fieldConfirmPassword.text.toString()

            // simpan data ke sharedpref
            if (passwordInput == confirmPasswordInput){
                val editor = sharedPreferences.edit()
                editor.putString("username", usernameInput)
                editor.putString("email", emailInput)
                editor.putString("phone", phoneInput)
                editor.putString("password", passwordInput)
                editor.putString("confirmPassword", confirmPasswordInput)

                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                val intentToLogin = Intent(this, LoginActivity::class.java)
                startActivity(intentToLogin)
                finish()
            } else {
                Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show()
            }

        }

    }
}