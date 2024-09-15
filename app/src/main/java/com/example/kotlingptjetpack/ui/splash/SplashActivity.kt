package com.example.kotlingptjetpack.ui.splash


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.kotlingptjetpack.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val adapter = SplashPagerAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }
}
