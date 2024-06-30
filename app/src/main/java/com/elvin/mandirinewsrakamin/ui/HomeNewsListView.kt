package com.elvin.mandirinewsrakamin.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elvin.mandirinewsrakamin.data.dto.NewsArticleDto
import com.elvin.mandirinewsrakamin.databinding.HomeNewsListFragmentBinding
import com.elvin.mandirinewsrakamin.repository.NewsApiRepository
import com.squareup.picasso.Picasso
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class HomeNewsListView : Fragment() {
    private var _binding : HomeNewsListFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit private var viewModel: HomeNewsListViewModel

    lateinit private var headLineImage : ImageFilterView
    lateinit private var headLineTitle  : TextView
    lateinit private var headLinePublisherName : TextView
    lateinit private var headLinePublishedDate : TextView

    lateinit private var newsListView : RecyclerView

    var adapter : NewsListRecyleViewAdapter = NewsListRecyleViewAdapter(emptyList())

    private val TAG = "HomeNewsListView"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeNewsListFragmentBinding.inflate(inflater, container, false)
        viewModel = HomeNewsListViewModel.provideFactory(repository = NewsApiRepository())

        newsListView = binding.newsList
        newsListView.layoutManager = LinearLayoutManager(requireContext())
        newsListView.adapter = adapter

        headLineImage = binding.breakingNewsPhoto
        headLineTitle = binding.breakingNewsTitle
        headLinePublisherName = binding.publisherName
        headLinePublishedDate = binding.datePublished

        observeNews()
        observeHeadLines()

        viewModel.fetchNews()
        viewModel.fetchHeadLines()
        return binding.root
    }

    fun observeNews() {
        viewModel.newsList.observe(viewLifecycleOwner){
            adapter.updateData(it)
        }
    }

    fun observeHeadLines() {
        viewModel.headLines.observe(viewLifecycleOwner){
            // Load image dari URL
            Log.d(TAG, "observeHeadLines: ini masuk${it.title}, ${it.author}, ${it.publishedAt}, ${it.imageUrl} ")
            updateHeadlines(it)
        }
    }

    fun updateHeadlines(news : NewsArticleDto){
        if (news.imageUrl != null) {
            Picasso.get()
                .load(news.imageUrl)
                .into(headLineImage)
        } else {
            headLineImage.setImageResource(android.R.drawable.ic_menu_report_image)
        }

        headLineTitle.text = news.title
        headLinePublisherName.text = news.author
        val zonedDateTime = ZonedDateTime.parse(news.publishedAt, DateTimeFormatter.ISO_ZONED_DATE_TIME)
        headLinePublishedDate.text = zonedDateTime.format(DateTimeFormatter.ofPattern("dd MMM, yyyy"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}