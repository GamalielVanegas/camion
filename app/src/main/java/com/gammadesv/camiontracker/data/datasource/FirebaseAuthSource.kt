package com.gammadesv.camiontracker.data.datasource

import com.gammadesv.camiontracker.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthSource @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun login(employeeCode: String, password: String): User {
        val query = firestore.collection("users")
            .whereEqualTo("employeeCode", employeeCode)
            .limit(1)
            .get()
            .await()

        if (query.isEmpty) throw Exception("Usuario no encontrado")

        val userDoc = query.documents[0]
        val email = userDoc.getString("email") ?: throw Exception("Email no encontrado")

        val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val firebaseUser = authResult.user ?: throw Exception("Error en autenticación")

        return User(
            id = firebaseUser.uid,
            name = userDoc.getString("name") ?: "",
            employeeCode = employeeCode,
            role = userDoc.getString("role") ?: "driver",
            email = email
        )
    }

    fun logout() = firebaseAuth.signOut()

    fun getCurrentUser(): User? {
        val firebaseUser = firebaseAuth.currentUser ?: return null
        return User(
            id = firebaseUser.uid,
            name = firebaseUser.displayName ?: "",
            email = firebaseUser.email ?: "",
            role = "driver"
        )
    }
}