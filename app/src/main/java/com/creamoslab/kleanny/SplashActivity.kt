package com.creamoslab.kleanny

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val mTimerToLoadLogin = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            loadLoginActivity()
        }, mTimerToLoadLogin)
    }

    private fun loadLoginActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
