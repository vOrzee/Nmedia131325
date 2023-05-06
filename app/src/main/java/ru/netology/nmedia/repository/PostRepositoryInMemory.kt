package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {

    private var nextId =0L
    private var posts = listOf(
        Post(
            id = ++nextId,
            author = "Нетология.!",
            content = "Это short пост.",
            published = "06 мая 2023 года ",
            likedByMe = false,
            likes = 0,
            reposts = 0,
            sees = 0
        ),        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет профессий будущего !",
            content = "Это второй пост. К нам приходят и начинающие специалисты, и руководители крупных компаний. Всех объединяет одно — желание добиться большего, чем есть сейчас.Мы даём те знания и навыки, которые помогают реализовать себя в профессии, больше зарабатывать, оптимизировать рутину и заниматься более сложными, но интересными задачами.  ",
            published = "20 апреля 2023 года ",
            likedByMe = false,
            likes = 0,
            reposts = 0,
            sees = 0
    ),
        Post(
            id = ++nextId,
            author = "Нетология. Университет интернет профессий будущего !",
            content = "Это первый пост. К нам приходят и начинающие специалисты, и руководители крупных компаний. Всех объединяет одно — желание добиться большего, чем есть сейчас.Мы даём те знания и навыки, которые помогают реализовать себя в профессии, больше зарабатывать, оптимизировать рутину и заниматься более сложными, но интересными задачами.  ",
            published = "08 апреля 2023 года ",
            likedByMe = false,
            likes =11,
            reposts = 1499999,
            sees = 1099
        )).reversed()

    private val data = MutableLiveData(posts)


    override fun getData(): LiveData<List<Post>> = data

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