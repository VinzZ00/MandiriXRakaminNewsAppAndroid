package com.elvin.mandirinewsrakamin.data

import com.elvin.mandirinewsrakamin.data.networkservice.NewsArticleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkServiceImplementation private constructor() {
    private val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    fun getNewsArticleService(): NewsArticleService {
        return retrofitBuilder.create(NewsArticleService::class.java)
    }

    companion object {
        private var instance: NetworkServiceImplementation? = null

        fun getInstance(): NetworkServiceImplementation {
            if (instance == null) {
                instance = NetworkServiceImplementation()
            }
            return instance!!
        }
    }
}