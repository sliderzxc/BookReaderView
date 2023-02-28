package com.sliderzxc.bookreader.ui.view

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.library.R
import com.main.library.databinding.BookReaderViewBinding
import com.sliderzxc.bookreader.data.FileType
import com.sliderzxc.bookreader.data.Page
import com.sliderzxc.bookreader.ui.adapter.BookPagesAdapter
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDDocumentInformation
import com.tom_roush.pdfbox.text.PDFTextStripper
import java.io.File
import java.io.IOException
import java.util.UUID

class BookReaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var binding: BookReaderViewBinding
    private var bookPagesAdapter = BookPagesAdapter()

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.book_reader_view, this, true)
        binding = BookReaderViewBinding.bind(view)
        binding.rvBookPages.adapter = bookPagesAdapter
        PDFBoxResourceLoader.init(context)
    }

    fun openBook(uri: Uri, fileType: FileType): Boolean {
        val stripper = PDFTextStripper()
        return try {
            val filePath = "BookExample.pdf"
            val inputStream = context.assets.open(filePath)
            val tempFile = File.createTempFile("BookExample", "pdf", context.cacheDir)
            tempFile.outputStream().use { outputStream -> inputStream.copyTo(outputStream) }
            PDDocument.load(tempFile).use { document ->
                val info: PDDocumentInformation = document.documentInformation
                document.pages.forEachIndexed { pageNumber, page ->
                    stripper.startPage = pageNumber
                    stripper.endPage = pageNumber
                    val pageText = stripper.getText(document)
                    val bookPage = Page(pageNumber, pageText)
                    bookPagesAdapter.addNewPage(bookPage)
                    invalidate()
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun doTest() {
        try {
            val filePath = "BookExample.pdf"
            val inputStream = context.assets.open(filePath)
            val tempFile = File.createTempFile("BookExample", "pdf", context.cacheDir)
            //val tempFile = File(Environment.getExternalStorageDirectory(), filePath)
            Log.d("MyLog", tempFile.path)
            tempFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }

            PDDocument.load(tempFile).use { document ->
                val pdfStripper = PDFTextStripper()
                val text = pdfStripper.getText(document)

                val info: PDDocumentInformation = document.documentInformation
                Log.d("MyLog", document.pages.count.toString())
                val title = info.title
                document.pages.forEachIndexed { index, page ->
                    pdfStripper.startPage = index
                    pdfStripper.endPage = index
                    val pageText = pdfStripper.getText(document)
                    Log.d("MyLog", "$index | $pageText")
                }
                val author = info.author
            }
        } catch (e: IOException) {
            Log.d("MyLog", "exception: ${e.message}")
        }
    }
}