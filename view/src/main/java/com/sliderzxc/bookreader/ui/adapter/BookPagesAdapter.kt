package com.sliderzxc.bookreader.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.main.library.R
import com.main.library.databinding.ItemPageBinding
import com.sliderzxc.bookreader.data.Page

class BookPagesAdapter : RecyclerView.Adapter<BookPagesAdapter.BookPagesViewHolder>() {
    private val pages = mutableListOf<Page>()

    class BookPagesViewHolder(view: View): ViewHolder(view) {
        private val binding by lazy { ItemPageBinding.bind(view) }

        fun bind(page: Page) {
            binding.tvPageText.text = page.text
            binding.tvPageNumber.text = page.pageNumber.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookPagesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return BookPagesViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookPagesViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    override fun getItemCount() = pages.size

    fun addNewPage(page: Page) {
        pages.add(page)
        notifyItemInserted(pages.size - 1)
    }
}