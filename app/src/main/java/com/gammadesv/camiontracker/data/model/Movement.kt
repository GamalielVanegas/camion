package com.gammadesv.camiontracker.data.model

import java.text.SimpleDateFormat
import java.util.*

data class Movement(
    val id: String = "",
    val driverId: String = "",
    val driverName: String = "",
    val branch: String = "",
    val branchId: String = "",
    val arrivalTime: String = "",
    val departureTime: String = "",
    val date: String = "",
    val duration: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val isSynced: Boolean = false
) {
    fun calculateDuration(): String {
        if (arrivalTime.isEmpty() || departureTime.isEmpty()) return ""

        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        val arrival = format.parse(arrivalTime)
        val departure = format.parse(departureTime)

        val diff = departure.time - arrival.time
        val hours = diff / (60 * 60 * 1000)
        val minutes = (diff / (60 * 1000)) % 60

        return "${hours}h ${minutes}m"
    }
}