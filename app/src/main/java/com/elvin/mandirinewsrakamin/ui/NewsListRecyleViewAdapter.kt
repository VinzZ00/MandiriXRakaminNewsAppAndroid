package com.elvin.mandirinewsrakamin.ui

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.elvin.mandirinewsrakamin.R
import com.elvin.mandirinewsrakamin.data.dto.NewsArticleDto
import com.squareup.picasso.Picasso
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class NewsListRecyleViewAdapter(private var news : List<NewsArticleDto>) : RecyclerView.Adapter<NewsListRecyleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        return ViewHolder(itemView)
    }

    fun updateData(newArticles: List<NewsArticleDto>) {
        news = newArticles
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = news[position]

        // Load image dari URL
        if (currentItem.imageUrl != null) {
            Picasso.get().load(currentItem.imageUrl).into(holder.imageView)
        } else {
            holder.imageView.setImageResource(android.R.drawable.ic_menu_report_image)
        }

        holder.title.text = currentItem.title
        holder.authorName.text = currentItem.author
        val zonedDateTime = ZonedDateTime.parse(currentItem.publishedAt, DateTimeFormatter.ISO_ZONED_DATE_TIME)
        holder.publishedAt.text = zonedDateTime.format(DateTimeFormatter.ofPattern("dd MMM, yyyy"))
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var imageView : ImageFilterView = itemView.findViewById(R.id.news_image)
        var title : TextView = itemView.findViewById(R.id.news_title)
        var authorName : TextView = itemView.findViewById(R.id.author_name)
        var publishedAt : TextView = itemView.findViewById(R.id.date_published)

    }
}