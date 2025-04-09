package com.gammadesv.camiontracker.data.datasource

import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LocationDataSource @Inject constructor(
    private val fusedLocationClient: FusedLocationProviderClient
) {
    suspend fun getCurrentLocation(): Location {
        return fusedLocationClient.lastLocation.await()
            ?: throw Exception("No se pudo obtener la ubicación")
    }
}