package com.library.bookreaderview

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.library.bookreaderview.databinding.ActivityMainBinding
import com.sliderzxc.bookreader.data.FileType

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bookReaderView.openBook(Uri.EMPTY, FileType.PDF)
    }
}