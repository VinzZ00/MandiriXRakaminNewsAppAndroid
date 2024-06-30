package com.elvin.mandirinewsrakamin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.elvin.mandirinewsrakamin.data.dto.FetchNewsDto
import com.elvin.mandirinewsrakamin.repository.NewsApiRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            var news : FetchNewsDto? = null
            try {
                news = async {NewsApiRepository().getNews() }.await()
            } catch (e : Exception) {
                Log.e(TAG, "onCreate: Error in get news : ",e)
            }
            Log.d(TAG, "onCreate: news count ${news?.articles?.size ?: 0}")
        }
    }
}