package com.sliderzxc.bookreader.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.main.library.R
import com.main.library.databinding.BookReaderViewBinding

class BookReaderView (
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var binding: BookReaderViewBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0, 0)
    constructor(context: Context): this(context, null, 0, 0)

    init {
        val inflater = LayoutInflater.from(context).inflate(R.layout.book_reader_view, this, true)
        binding = BookReaderViewBinding.bind(inflater)
    }

}