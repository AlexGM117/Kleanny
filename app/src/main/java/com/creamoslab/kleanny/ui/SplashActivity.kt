package com.creamoslab.kleanny.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.creamoslab.kleanny.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val mTimerToLoadLogin = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Glide.with(this)
            .load(R.raw.kleanny_splash_2)
            .into(gifView)

        Handler().postDelayed({
            loadLoginActivity()
        }, mTimerToLoadLogin)
    }

    private fun loadLoginActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
