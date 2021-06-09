package com.example.classificationcovid.web

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.classificationcovid.R


class Information : AppCompatActivity() {

    private fun backBar() {
        val backbtn = supportActionBar
        backbtn!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun mainTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        var intent = Intent(this, WebActivity::class.java)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        backBar()
        mainTitle("Information")
    }

}