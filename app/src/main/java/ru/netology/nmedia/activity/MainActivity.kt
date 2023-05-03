package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adaptor.PostAdaptor
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val viewModel: PostViewModel by viewModels()

        val adaptor = PostAdaptor ({
            viewModel.likesById(it.id)},
            {
            viewModel.repostsById(it.id)},
            {
            viewModel.seesById(it.id)}
    )

        viewModel.data.observe(this) { posts ->
            adaptor.submitList(posts)
        }
        activityMainBinding.List.adapter = adaptor
    }
}

