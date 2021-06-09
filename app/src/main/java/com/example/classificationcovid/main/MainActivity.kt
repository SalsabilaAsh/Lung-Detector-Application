package com.example.classificationcovid.main

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.classificationcovid.R
import com.example.classificationcovid.web.WebActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        var buttonWeb = findViewById(R.id.button_to_web) as Button
        buttonWeb.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    WebActivity::class.java
                )
            )
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}