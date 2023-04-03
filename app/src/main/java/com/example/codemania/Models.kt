package com.example.codemania

data class ModelAuth(
    val email: String,
    val password: String
)
data class ModelReg(
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val password: String,
    val dateBirthDay: String,
    val sex: String
)
data class ModelUser(
    val userId: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val avatar: String
)
data class ModelIdentity(
    val token: String,
    val user: ModelUser
)
data class ModelToken(
    val token: String
)