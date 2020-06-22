package com.vkr.vkrmobile.ui.global.list.news

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.vkr.vkrmobile.R
import com.vkr.vkrmobile.model.data.net.response.news.NewsResponse
import com.vkr.vkrmobile.ui.global.inflate
import com.vkr.vkrmobile.ui.global.loadImage
import kotlinx.android.synthetic.main.news_card_item.view.newsLogo
import kotlinx.android.synthetic.main.news_card_item.view.newsText
import kotlinx.android.synthetic.main.news_card_item.view.newsTitle
import kotlinx.android.synthetic.main.news_expandable_card_item.view.*

class ExpandableCardsNewsAdapterDelegate(
    private val menuViewMode: String
) : AdapterDelegate<MutableList<NewsResponse>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.news_expandable_card_item))
    }

    override fun isForViewType(items: MutableList<NewsResponse>, position: Int): Boolean {
        return menuViewMode == "Expandable"
    }

    override fun onBindViewHolder(
        items: MutableList<NewsResponse>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position])
    }

    private inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: NewsResponse) {
            with(view) {
                newsLogo.loadImage(
                    url = item.logo,
                    placeholderDrawable = ContextCompat.getDrawable(context, R.drawable.ic_placeholder)
                )
                newsTitle.text = item.title
                newsText.text = item.text

                expandIcon.setOnClickListener {
                    if (newsText.visibility == View.GONE) {
                        expandIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_top))
                        newsText.visibility == View.VISIBLE
                    } else {
                        expandIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_arrow_bottom))
                        newsText.visibility == View.GONE
                    }
                }
            }
        }
    }

}