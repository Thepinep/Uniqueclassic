package com.example.uniqueclassic.Pdf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uniqueclassic.R
import com.github.barteksc.pdfviewer.PDFView

class PolicyActivity : AppCompatActivity() {

    lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_policy)

        pdfView = findViewById(R.id.pdfView)

        pdfView.fromAsset("PrivacyPolicy.pdf").load()
    }
}