package com.gammadesv.camiontracker.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import androidx.work.*
import com.gammadesv.camiontracker.data.repository.MovementRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SyncService : LifecycleService() {
    @Inject lateinit var repository: MovementRepository

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        val syncWork = PeriodicWorkRequestBuilder<SyncWorker>(
            15, TimeUnit.MINUTES
        ).setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "syncWork",
            ExistingPeriodicWorkPolicy.KEEP,
            syncWork
        )

        return START_STICKY
    }

    class SyncWorker(
        context: Context,
        workerParams: WorkerParameters
    ) : CoroutineWorker(context, workerParams) {
        @Inject lateinit var repository: MovementRepository

        override suspend fun doWork(): Result {
            return try {
                repository.syncPendingMovements()
                Result.success()
            } catch (e: Exception) {
                Result.retry()
            }
        }
    }
}