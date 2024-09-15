package com.example.kotlingptjetpack.data.remote

import com.example.kotlingptjetpack.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

// Retrofit interface to define API endpoints//
interface NewsApiService {
    @GET("top-headlines?country=${Constants.COUNTRY}&apiKey=${Constants.API_KEY}")
    suspend fun getTopHeadlines(): Response<NewsResponse>
}
