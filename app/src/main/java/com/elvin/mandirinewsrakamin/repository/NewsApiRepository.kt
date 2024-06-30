package com.elvin.mandirinewsrakamin.repository

import android.util.Log
import com.elvin.mandirinewsrakamin.data.NetworkServiceImplementation
import com.elvin.mandirinewsrakamin.data.dto.FetchNewsDto
import com.elvin.mandirinewsrakamin.data.networkservice.NewsArticleService
import java.text.SimpleDateFormat
import java.util.Date

class NewsApiRepository {
    var newsArticleService: NewsArticleService = NetworkServiceImplementation.getInstance().getNewsArticleService()

    suspend fun getNews() : FetchNewsDto {
        var min30DayAgo : Date = Date(System.currentTimeMillis() - ((30 * 24 * 60 * 60 * 1000L)))
        var dateFormat : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        return newsArticleService.getNews(
            from = dateFormat.format(min30DayAgo),
            query = "Indonesia"
        )
    }

    suspend fun getTopHeadlines() : FetchNewsDto {
        return newsArticleService.getTopHeadlines()
    }

}