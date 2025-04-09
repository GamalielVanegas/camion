package com.gammadesv.camiontracker.data.repository

import com.gammadesv.camiontracker.data.model.User
import com.gammadesv.camiontracker.data.datasource.FirebaseAuthSource
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authSource: FirebaseAuthSource
) {
    suspend fun login(employeeCode: String, password: String): User {
        return authSource.login(employeeCode, password)
    }

    fun logout() = authSource.logout()

    fun getCurrentUser(): User? = authSource.getCurrentUser()
}