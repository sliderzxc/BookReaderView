package com.sliderzxc.bookreader.ui.view

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.main.library.R
import com.main.library.databinding.BookReaderViewBinding
import com.sliderzxc.bookreader.data.FileType
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.pdmodel.PDDocumentInformation
import com.tom_roush.pdfbox.text.PDFTextStripper
import java.io.File
import java.io.IOException

class BookReaderView (
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var binding: BookReaderViewBinding

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0, 0)
    constructor(context: Context): this(context, null, 0, 0)

    init {
        val inflater = LayoutInflater.from(context).inflate(R.layout.book_reader_view, this, true)
        binding = BookReaderViewBinding.bind(inflater)
        PDFBoxResourceLoader.init(context)
    }

    fun openBook(uri: Uri, fileType: FileType): Boolean {
        return try {
            val filePath = uri.path.toString()
            val inputStream = context.assets.open(filePath)
            val tempFile = File.createTempFile(filePath, fileType.name, context.cacheDir)
            tempFile.outputStream().use { outputStream -> inputStream.copyTo(outputStream) }
            PDDocument.load(tempFile).use { document ->
                val info: PDDocumentInformation = document.documentInformation
                document.pages.forEach {

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
//            val tempFile = File.createTempFile("BookExample", "pdf", context.cacheDir)
            val tempFile = File(Environment.getExternalStorageDirectory(), filePath)
            Log.d("MyLog", tempFile.path)
            tempFile.outputStream().use { outputStream ->
                inputStream.copyTo(outputStream)
            }

            PDDocument.load(tempFile).use { document ->
                val pdfStripper = PDFTextStripper()
                val text = pdfStripper.getText(document)

                val info: PDDocumentInformation = document.documentInformation
                val title = info.title
                document.pages.forEach {

                }
                val author = info.author

                Log.d("MyLog", "Title: $title")
                Log.d("MyLog", "Author: $author")
                Log.d("MyLog", "Text: $text")
            }
        } catch (e: IOException) {
            Log.d("MyLog", "exception: ${e.message}")
        }
    }
}