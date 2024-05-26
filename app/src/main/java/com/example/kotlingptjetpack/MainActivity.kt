package com.example.kotlingptjetpack

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Set up RecyclerView in your activity or fragment.
class MainActivity : AppCompatActivity() {

    // Declare variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize RecyclerView and layout manager
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsApiService::class.java)

        // Fetch data
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTopHeadlines()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    newsResponse?.let {
                        adapter = NewsAdapter(it.articles)
                        recyclerView.adapter = adapter
                    }
                } else {
                    // Handle error
                    Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
