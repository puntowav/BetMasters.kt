package com.example.betmasters

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.betmasters.Login.LoginRepository
import com.example.betmasters.Login.LoginViewModel
import com.example.betmasters.databinding.ActivityRegistreBinding

class Registre : AppCompatActivity() {
    private lateinit var binding: ActivityRegistreBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = LoginRepository()
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel() as T
            }
        })[LoginViewModel::class.java]

        binding.register.setOnClickListener {
            val accepted = binding.cbTerms.isChecked

            val formErrors = repo.validateFormDetailed(
                binding.etUsername.text.toString(),
                binding.etMail.text.toString(),
                binding.etPass.text.toString(),
                binding.etPass2.text.toString(),
                accepted
            )

            binding.etUsername.error = formErrors.usernameError
            binding.etMail.error = formErrors.emailError
            binding.etPass.error = formErrors.passwordError
            binding.etPass2.error = formErrors.confirmPasswordError
        }

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}