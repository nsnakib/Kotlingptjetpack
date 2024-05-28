package com.example.kotlingptjetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NewsViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun fetchTopHeadlines(service: NewsApiService) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getTopHeadlines()
                if (response.isSuccessful) {
                    _articles.postValue(response.body()?.articles ?: emptyList())
                } else {
                    _error.postValue("Error fetching data")
                }
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            }
        }
    }
}