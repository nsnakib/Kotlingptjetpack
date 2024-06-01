package com.example.kotlingptjetpack


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val adapter = SplashPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }
}
