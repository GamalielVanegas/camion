package com.gammadesv.camiontracker.data.repository

import com.gammadesv.camiontracker.data.model.Branch
import com.gammadesv.camiontracker.data.datasource.FirestoreSource
import javax.inject.Inject

class BranchRepository @Inject constructor(
    private val firestoreSource: FirestoreSource
) {
    suspend fun getAllBranches(): List<Branch> {
        return firestoreSource.getBranches()
    }

    suspend fun getBranchById(branchId: String): Branch? {
        return firestoreSource.getBranchById(branchId)
    }
}