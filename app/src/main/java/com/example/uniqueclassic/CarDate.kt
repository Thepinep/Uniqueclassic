package com.example.uniqueclassic

import android.os.Parcelable
import com.google.android.material.datepicker.CalendarConstraints
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class CarDate( val istniejaceDaty: List<String>) :CalendarConstraints.DateValidator,Parcelable {

    val Daty: List<Pair<Calendar, Calendar>> = istniejaceDaty.map{
        DateSerializer().deserializuj(it)}
        .map {
          val  cal= Calendar.getInstance()
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            cal.setTime(sdf.parse(it.first))
      //      val startepoch = cal.timeInMillis
            val  cal2= Calendar.getInstance()
            cal2.setTime(sdf.parse(it.second))
     //       val endepoch = cal.timeInMillis
            Pair(cal,cal2)
        }

    override fun isValid(date: Long): Boolean {
        val  cal= Calendar.getInstance()
        cal.timeInMillis = date
        val hasThisDate = Daty.any {
            cal.equals(it.first) ||
                    cal.equals(it.second) ||
                    cal.after(it.first ) && cal.before(it.second)
        }
        return Daty.isEmpty() || hasThisDate.not()
    }
    }
