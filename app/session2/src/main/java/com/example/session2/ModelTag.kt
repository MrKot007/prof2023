package com.example.session2

data class ModelTag(
    val id: Int,
    val name: String
)

data class ModelError(
    val error: String
)
data class ModelCourse(
    val courseId: Int,
    val title: String,
    val tags: List<Int>,
    val cover: String,
    val price: Int,
)