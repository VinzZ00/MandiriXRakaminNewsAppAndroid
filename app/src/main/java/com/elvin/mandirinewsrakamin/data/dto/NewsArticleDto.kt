package com.elvin.mandirinewsrakamin.data.dto

import com.google.gson.annotations.SerializedName

data class NewsArticleDto(
    @SerializedName("title") val title : String,
    @SerializedName("description") var description : String,
    @SerializedName("urlToImage") var imageUrl : String,
    @SerializedName("publishedAt") val publishedAt : String,
    @SerializedName("author") var author : String
)