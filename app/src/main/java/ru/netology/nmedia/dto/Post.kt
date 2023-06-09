package ru.netology.nmedia.dto

data class Post(
    val id : Long,
    val author : String,
    val content : String,
    val published : String,
    val likedByMe : Boolean = false,
    val likes: Int = 0,
    val reposts: Int = 0,
    val sees: Int = 0,
    val video: String
)
