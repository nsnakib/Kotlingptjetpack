package com.example.kotlingptjetpack
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val description: String?,
    val url: String?
)