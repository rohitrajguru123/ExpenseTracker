package com.example.expensetrackerrohit.auth

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.expensetrackerrohit.R
import com.example.expensetrackerrohit.viewmodel.AuthViewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private lateinit var authViewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

        val signInButton = view.findViewById<Button>(R.id.signInButton)
        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            authViewModel.signIn(email, password).observe(viewLifecycleOwner) {
                // Handle authentication result
            }
        }

        val signUpText = view.findViewById<TextView>(R.id.signUpText)
        signUpText.setOnClickListener {
            // Navigate to sign-up screen
        }
    }
}
