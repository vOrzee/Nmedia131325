package ru.netology.nmedia.repository

import android.content.Intent
import androidx.activity.result.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.NewPostActivity
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {

    private var nextId = 0L
    private var posts = listOf(
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет профессий будущего !",
            content = "Это short пост. ",
            published = "12 мая 2023 года ",
            likedByMe = false,
            likes = 11,
            reposts = 1499999,
            sees = 1099,
            video = "https://www.youtube.com/watch?v=yIPqxSgObKg"
        )
    ).reversed()

    private val data = MutableLiveData(posts)


    override fun getData(): LiveData<List<Post>> = data

    override fun editById(post: Post) {
        if (post.id != 0L) {
        posts = posts.map {
            if (post.id != it.id) it
            else it.copy(content = post.content)
        }
        data.value = posts
    }
}

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                if (post.likedByMe) {
                    post.copy(likedByMe = !post.likedByMe, likes = post.likes - 1)
                }
                else {
                    post.copy(likedByMe = !post.likedByMe, likes = post.likes + 1)
                }
            }
             else {
                post
            }
        }
        data.value = posts
    }

    override fun repostById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(reposts = post.reposts+1)
            } else {
                post
            }
        }
          data.value = posts
        }

    override fun seeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(sees = post.sees+1)
            } else {
                post
            }
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter{it.id != id}
        data.value = posts
    }

    override fun save(post: Post) {
        if(post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = ++nextId,
                    author = "Me",
                    published = "now",
                    likedByMe = false,
                )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (post.id != it.id) it
            else it.copy(content = post.content)
            }
        data.value = posts
        }

    }