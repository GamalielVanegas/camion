package com.gammadesv.camiontracker.data.local

import androidx.room.*
import com.gammadesv.camiontracker.data.model.Movement
import kotlinx.coroutines.flow.Flow

@Dao
interface MovementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovement(movement: Movement)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovements(movements: List<Movement>)

    @Update
    suspend fun updateMovement(movement: Movement)

    @Query("SELECT * FROM movements WHERE driverId = :driverId AND date = :date")
    suspend fun getMovementsByDriverAndDate(driverId: String, date: String): List<Movement>

    @Query("SELECT * FROM movements WHERE date = :date")
    suspend fun getMovementsByDate(date: String): List<Movement>

    @Query("SELECT * FROM movements WHERE isSynced = 0")
    suspend fun getUnsyncedMovements(): List<Movement>
}