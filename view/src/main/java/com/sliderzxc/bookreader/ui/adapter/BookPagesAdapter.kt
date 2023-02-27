package com.sliderzxc.bookreader.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.main.library.databinding.ItemPageBinding
import com.sliderzxc.bookreader.data.Page

class BookPagesAdapter : RecyclerView.Adapter<BookPagesAdapter.BookPagesViewHolder>() {
    private val pages = mutableListOf<Page>()

    class BookPagesViewHolder(view: View): ViewHolder(view) {
        private val binding by lazy { ItemPageBinding.bind(view) }

        fun bind(page: Page) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookPagesViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BookPagesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = pages.size
}