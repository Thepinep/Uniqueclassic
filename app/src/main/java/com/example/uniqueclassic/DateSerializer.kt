package com.example.uniqueclassic

class DateSerializer {

    companion object {
        private const val DELIMITER = "/"
    }

    fun serializuj(etStartDate: String, etEndDate: String): String = "$etStartDate$DELIMITER$etEndDate"
    fun deserializuj(zserializowany: String) = zserializowany.split(DELIMITER).let {
        Pair(it.get(0), it.get(1))
    }
}