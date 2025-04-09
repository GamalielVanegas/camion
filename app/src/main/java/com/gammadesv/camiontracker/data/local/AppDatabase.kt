package com.gammadesv.camiontracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gammadesv.camiontracker.data.model.Movement

@Database(
    entities = [Movement::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movementDao(): MovementDao
}