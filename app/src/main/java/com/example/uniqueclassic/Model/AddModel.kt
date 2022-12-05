package com.example.uniqueclassic.Model

data class AddModel (
    var etId: String? = null,
    var etTitle: String? = null,
    var etVehicle: String? = null,
    var etDescription: String? = null,
    var etSeller: String? = null,
    var etPrice: String? = null,
    var etVin: String? = null,
    val etYear: String? = null,
    val etPower: String? = null,
    val etCubic: String? = null,
    val etFuel: String? = null,
    val etBody: String? = null,
    val etKilometre: String? = null,
    val etColor: String? = null,
    val etCondition: String? = null,
    val etTransmission: String? = null,
    val etCountry: String? = null,
    val etWheel: String? = null,
    val etPhone: String? = null,
    val etUsername: String? = null,
    val etLocation: String? = null,
    val etgalery : List<String> = emptyList()
        )
