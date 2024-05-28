package com.example.kotlingptjetpack

import retrofit2.Response
import retrofit2.http.GET

// Retrofit interface to define API endpoints//
interface NewsApiService {
    @GET("top-headlines?country=us&apiKey=a7ebed0b55b041e7a20fc6ef542c5322")
    suspend fun getTopHeadlines(): Response<NewsResponse>
}
