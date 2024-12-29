package com.example.expensetrackerrohit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.expensetrackerrohit.auth.SignInFragment
import com.example.expensetrackerrohit.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handler to navigate after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SignInFragment::class.java))
            finish() // Close SplashActivity
        }, 3000) // 3 seconds delay
    }
}
