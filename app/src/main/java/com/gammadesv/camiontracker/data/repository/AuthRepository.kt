class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun login(email: String, password: String): Resource<User> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user ?: return Resource.Error("Usuario no encontrado")

            val document = firestore.collection("users").document(user.uid).get().await()
            val userData = document.toObject(User::class.java) ?: return Resource.Error("Datos de usuario no encontrados")

            Resource.Success(userData)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error en autenticación")
        }
    }
}