package com.example.kotlingptjetpack

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val articleDao: ArticleDao = AppDatabase.getDatabase(application).articleDao()

    fun fetchTopHeadlines(service: NewsApiService) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.getTopHeadlines()
                if (response.isSuccessful) {
                    response.body()?.articles?.let {
                        _articles.postValue(it)
                        saveArticlesToLocalDatabase(it)
                    }
                } else {
                    _error.postValue("Error fetching data")
                    loadArticlesFromLocalDatabase()
                }
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
                loadArticlesFromLocalDatabase()
            }
        }
    }

    private suspend fun saveArticlesToLocalDatabase(articles: List<Article>) {
        articleDao.deleteAllArticles()
        articleDao.insertArticles(articles)
    }

    private fun loadArticlesFromLocalDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            articleDao.getAllArticles()
                .catch { e -> _error.postValue("Error: ${e.message}") }
                .collect { localArticles ->
                    _articles.postValue(localArticles)
                }
        }
    }
}