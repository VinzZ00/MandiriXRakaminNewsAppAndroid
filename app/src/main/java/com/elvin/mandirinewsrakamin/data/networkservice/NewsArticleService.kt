package com.elvin.mandirinewsrakamin.data.networkservice

import com.elvin.mandirinewsrakamin.data.dto.FetchNewsDto
import com.elvin.mandirinewsrakamin.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsArticleService {

    @GET("/v2/everything")
    suspend fun getNews(
        @Query("from") from: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("q") query: String,
        @Query("language") lang : String = "id",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ) : FetchNewsDto

    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "id",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ) : FetchNewsDto
}