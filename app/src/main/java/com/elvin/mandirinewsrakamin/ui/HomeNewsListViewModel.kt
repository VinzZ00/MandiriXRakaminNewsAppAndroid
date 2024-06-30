package com.elvin.mandirinewsrakamin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.elvin.mandirinewsrakamin.data.dto.NewsArticleDto
import com.elvin.mandirinewsrakamin.repository.NewsApiRepository
import kotlinx.coroutines.async


class HomeNewsListViewModel(private val repository : NewsApiRepository) : ViewModel() {
    private var _newsList : MutableLiveData<List<NewsArticleDto>> = MutableLiveData<List<NewsArticleDto>>(
        emptyList()
    )

    private var _headLines : MutableLiveData<NewsArticleDto> = MutableLiveData<NewsArticleDto>()

    val newsList : LiveData<List<NewsArticleDto>>
        get() = _newsList

    val headLines : LiveData<NewsArticleDto>
        get() = _headLines

    fun fetchHeadLines() {
        viewModelScope.async {
            var fetchedNewsDto = repository.getTopHeadlines()
            if (fetchedNewsDto.articles.filter {
                it.imageUrl != null
            }.isEmpty()) {
                fetchedNewsDto = repository.getNews()
            }
            _headLines.postValue(fetchedNewsDto.articles.first())
        }
    }

    fun fetchNews() {
        viewModelScope.async {
            var fetchedNewsDto = repository.getNews()
            _newsList.postValue(fetchedNewsDto.articles)
        }
    }

    companion object {
        fun provideFactory(repository: NewsApiRepository) : HomeNewsListViewModel {
            return HomeNewsListViewModelFactory(repository).create(HomeNewsListViewModel::class.java)
        }

        private class HomeNewsListViewModelFactory(private val repository : NewsApiRepository) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeNewsListViewModel::class.java)) {
                    return HomeNewsListViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}