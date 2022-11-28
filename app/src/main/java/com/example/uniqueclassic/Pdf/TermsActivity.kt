package com.example.uniqueclassic.Pdf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uniqueclassic.R
import com.github.barteksc.pdfviewer.PDFView

class TermsActivity : AppCompatActivity() {

    lateinit var pdfView: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        pdfView = findViewById(R.id.pdfView)

        pdfView.fromAsset("TermsandConditions.pdf").load()
    }
}