package com.example.kotlingptjetpack.data.remote

import com.example.kotlingptjetpack.data.model.Article

// Data classes to represent the JSON response
data class NewsResponse(
    val articles: List<Article>
)