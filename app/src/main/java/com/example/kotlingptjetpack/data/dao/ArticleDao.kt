package com.example.kotlingptjetpack.data.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlingptjetpack.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert
    fun insertArticles(articles: List<Article>)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Query("DELETE FROM articles")
    fun deleteAllArticles(): Int
}