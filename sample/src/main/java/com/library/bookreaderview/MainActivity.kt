package com.library.bookreaderview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.library.bookreaderview.databinding.ActivityMainBinding
import com.sliderzxc.bookreader.data.FileType

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.bookReaderView.doTest()
    }
}