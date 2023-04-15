package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemory : PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет профессий будущего !",
        content = "К нам приходят и начинающие специалисты, и руководители крупных компаний. Всех объединяет одно — желание добиться большего, чем есть сейчас.Мы даём те знания и навыки, которые помогают реализовать себя в профессии, больше зарабатывать, оптимизировать рутину и заниматься более сложными, но интересными задачами.  ",
        published = "08 апреля 2023 года ",
        likeByMe = false,
        likes =11,
        reposts = 1499999,
        sees = 1099
    )

    private val data = MutableLiveData(post)

    override fun getData(): LiveData<Post> = data

    override fun like() {
        post =  post.copy(
        likes = if (post.likeByMe) post.likes - 1 else post.likes + 1,
        likeByMe = !post.likeByMe
        )
        data.value = post
    }

    override fun repost() {
        post =  post.copy(
            reposts = post.reposts + 1,
        )
        data.value = post
    }

    override fun see() {
        post =  post.copy(
            sees = post.sees + 1,
        )
        data.value = post
    }


}