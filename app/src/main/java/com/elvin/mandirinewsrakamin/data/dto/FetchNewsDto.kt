package com.elvin.mandirinewsrakamin.data.dto

import com.google.gson.annotations.SerializedName

data class FetchNewsDto (
    @SerializedName("status") val status : String,
    @SerializedName("articles") val articles : List<NewsArticleDto>
)