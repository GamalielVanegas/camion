package com.gammadesv.camiontracker.data.model

data class Branch(
    val id: String = "",
    val name: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val radiusMeters: Int = 100
)