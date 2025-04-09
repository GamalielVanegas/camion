package com.gammadesv.camiontracker.data.repository

import com.gammadesv.camiontracker.data.local.MovementDao
import com.gammadesv.camiontracker.data.model.Movement
import com.gammadesv.camiontracker.data.datasource.FirestoreSource
import com.gammadesv.camiontracker.utils.ConnectivityHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovementRepository @Inject constructor(
    private val firestoreSource: FirestoreSource,
    private val movementDao: MovementDao,
    private val connectivityHelper: ConnectivityHelper
) {
    suspend fun saveMovement(movement: Movement): String {
        return if (connectivityHelper.isOnline()) {
            firestoreSource.saveMovement(movement)
        } else {
            movementDao.insertMovement(movement)
            movement.id
        }
    }

    suspend fun getMovementsByDriverAndDate(driverId: String, date: String): List<Movement> {
        return if (connectivityHelper.isOnline()) {
            firestoreSource.getMovementsByDriverAndDate(driverId, date)
        } else {
            movementDao.getMovementsByDriverAndDate(driverId, date)
        }
    }

    suspend fun getAllMovementsByDate(date: String): List<Movement> {
        return if (connectivityHelper.isOnline()) {
            firestoreSource.getAllMovementsByDate(date)
        } else {
            movementDao.getMovementsByDate(date)
        }
    }

    suspend fun syncPendingMovements() {
        if (!connectivityHelper.isOnline()) return

        val pendingMovements = movementDao.getUnsyncedMovements()
        pendingMovements.forEach { movement ->
            try {
                val id = firestoreSource.saveMovement(movement)
                movementDao.updateMovement(movement.copy(id = id, isSynced = true))
            } catch (e: Exception) {
                // Log error
            }
        }
    }
}