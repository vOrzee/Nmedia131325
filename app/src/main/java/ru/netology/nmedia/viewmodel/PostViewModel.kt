package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemory

class PostViewModel :ViewModel() {
    private val repository : PostRepository = PostRepositoryInMemory()

    val data : LiveData<List<Post>> = repository.getData()
    fun likesById(id:Long) = repository.likeById(id)
    fun repostsById(id:Long) = repository.repostById(id)
    fun seesById(id:Long) = repository.seeById(id)
}