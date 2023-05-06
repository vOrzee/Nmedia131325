package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adaptor.PostAdaptor
import ru.netology.nmedia.adaptor.PostListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.AndroidUtils
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val viewModel: PostViewModel by viewModels()

        val adaptor = PostAdaptor (
            object : PostListener{
                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }

                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }

                override fun onLike(post: Post) {
                    viewModel.likesById(post.id)
                }

                override fun onView(post: Post) {
                    viewModel.repostsById(post.id)
                }

                override fun onSee(post: Post) {
                    viewModel.seesById(post.id)
                }

                override fun onCancel(post: Post) {
                    viewModel.clearEdit(post)
                }
            }
    )
        viewModel.edited.observe(this){
            if(it.id == 0L) return@observe
            activityMainBinding.cancel.visibility = View.VISIBLE
            activityMainBinding.content.requestFocus()
            activityMainBinding.content.setText(it.content)
        }

        activityMainBinding.save.setOnClickListener{
            with(activityMainBinding.content){
                val content = text?.toString()
                if(content.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Context must not be empty",
                        Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(content)
                viewModel.save()
                activityMainBinding.cancel.visibility = View.INVISIBLE

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        activityMainBinding.cancel.setOnClickListener{
            with(activityMainBinding.content){
                activityMainBinding.cancel.visibility = View.INVISIBLE
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        viewModel.data.observe(this) { posts ->
            adaptor.submitList(posts)
        }
        activityMainBinding.List.adapter = adaptor
    }
}

