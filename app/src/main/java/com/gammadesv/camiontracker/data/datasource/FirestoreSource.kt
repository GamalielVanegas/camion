package com.gammadesv.camiontracker.data.datasource

import com.gammadesv.camiontracker.data.model.Branch
import com.gammadesv.camiontracker.data.model.Movement
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend fun saveMovement(movement: Movement): String {
        val document = firestore.collection("movements").add(movement).await()
        return document.id
    }

    suspend fun getMovementsByDriverAndDate(driverId: String, date: String): List<Movement> {
        return firestore.collection("movements")
            .whereEqualTo("driverId", driverId)
            .whereEqualTo("date", date)
            .get()
            .await()
            .toObjects(Movement::class.java)
    }

    suspend fun getAllMovementsByDate(date: String): List<Movement> {
        return firestore.collection("movements")
            .whereEqualTo("date", date)
            .get()
            .await()
            .toObjects(Movement::class.java)
    }

    suspend fun getBranches(): List<Branch> {
        return firestore.collection("branches")
            .get()
            .await()
            .toObjects(Branch::class.java)
    }

    suspend fun getBranchById(branchId: String): Branch? {
        return firestore.collection("branches")
            .document(branchId)
            .get()
            .await()
            .toObject(Branch::class.java)
    }
}