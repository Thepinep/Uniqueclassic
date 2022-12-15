package com.example.uniqueclassic.reservations

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uniqueclassic.R
import org.w3c.dom.Text


class ReservationsActivity : AppCompatActivity() {

    private  lateinit var sum: TextView
    private  lateinit var vat: TextView
    private  lateinit var fee: TextView
    private  lateinit var subtotal: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations)

        val buttonClick = findViewById<ImageView>(R.id.back2_click)
        buttonClick.setOnClickListener {
            finish()
        }



        sum = findViewById(R.id.sum_text)
        vat = findViewById(R.id.vat_text)
        fee = findViewById(R.id.fee_text)
        subtotal = findViewById(R.id.sum_vat_text_fee)



        val inputText = sum.text.toString()
        val inputValue = inputText.toDouble()
        val discount = inputValue * 1.23
        val result =   discount - inputValue
        val formattedResult = "%.2f".format(result) +" pln"
       vat.text = formattedResult

        val inputText2 = sum.text.toString()
        val inputValue2 = inputText2.toDouble()
        val discount2 = inputValue2 * 1.1
        val result2 =   discount2 - inputValue2
        val formattedResult2 = "%.2f".format(result2)+" pln"
        fee.text = formattedResult2


        val inputText3 = sum.text.toString()
        val inputValue3 = inputText3.toDouble()
        val discount3 = inputValue3 * 0.23
        val discount4 = inputValue3 * 0.10
        val result3 = inputValue3 - discount3 -discount4
        val formattedResult3 = "%.2f".format(result3)+" pln"
        subtotal.text = formattedResult3


    }
}





