package android.example.exercise3

import java.util.Date

data class NewsItem(
    val title: String,
    val imageUrl: String,
    val category: Category,
    val publishDate: Date,
    val previewText: String,
    val fullText: String)