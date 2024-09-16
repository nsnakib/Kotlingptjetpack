package com.example.kotlingptjetpack.ui.main
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.kotlingptjetpack.ui.news.NewsFragment
import com.example.kotlingptjetpack.R
import com.example.kotlingptjetpack.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "MainActivity onCreate called")
        // Enqueue the LogWorker
        val logWorkerRequest = OneTimeWorkRequest.Builder(LogWorker::class.java)
            .build()

        WorkManager.getInstance(this).enqueue(logWorkerRequest)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsFragment())
                .commit()
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> selectedFragment = NewsFragment() // Replace with appropriate fragment
                R.id.nav_search -> selectedFragment = NewsFragment() // Replace with appropriate fragment
                R.id.nav_profile -> selectedFragment = NewsFragment() // Replace with appropriate fragment
                R.id.nav_settings -> selectedFragment = SettingsFragment() // Replace with appropriate fragment
                R.id.nav_more -> selectedFragment = NewsFragment() // Replace with appropriate fragment
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
            }
            true
        }
    }
}
