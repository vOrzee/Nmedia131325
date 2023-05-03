package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {

    private var post = listOf(
        Post(
            id = 2,
            author = "Нетология. Университет интернет профессий будущего !",
            content = "Это второй пост. К нам приходят и начинающие специалисты, и руководители крупных компаний. Всех объединяет одно — желание добиться большего, чем есть сейчас.Мы даём те знания и навыки, которые помогают реализовать себя в профессии, больше зарабатывать, оптимизировать рутину и заниматься более сложными, но интересными задачами.  ",
            published = "20 апреля 2023 года ",
            likedByMe = false,
            likes = 0,
            reposts = 0,
            sees = 0
    ),
        Post(
            id = 1,
            author = "Нетология. Университет интернет профессий будущего !",
            content = "Это первый пост. К нам приходят и начинающие специалисты, и руководители крупных компаний. Всех объединяет одно — желание добиться большего, чем есть сейчас.Мы даём те знания и навыки, которые помогают реализовать себя в профессии, больше зарабатывать, оптимизировать рутину и заниматься более сложными, но интересными задачами.  ",
            published = "08 апреля 2023 года ",
            likedByMe = false,
            likes =11,
            reposts = 1499999,
            sees = 1099
        ))

    private val data = MutableLiveData(post)

    override fun getData(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy(likedByMe = !post.likedByMe)
            } else {
                post
            }
        }
        data.value = post
    }

    override fun repostById(id: Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy()
            } else {
                post
            }
        }
          data.value = post
        }

    override fun seeById(id: Long) {
        post = post.map { post ->
            if (post.id == id) {
                post.copy()
            } else {
                post
            }
        }
        data.value = post
    }


}