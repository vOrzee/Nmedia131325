package ru.netology.nmedia.dto

data class Post(
    val id : Long,
    val author : String,
    val content : String,
    val published : String,
    var likeByMe : Boolean = false,
    var likes: Int = 0,
    var repost: Int = 0,
    var see: Int = 0
)
