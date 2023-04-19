package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
        with(binding) {
            authorTextView.text = post.author
            dateTextView.text = post.published
            aboutTextView.text = post.content
            loveTextView.text = Calculate(post.likes)
            repostTextView.text = Calculate(post.reposts)
            seeTextView.text = Calculate(post.sees)
            if (post.likeByMe) loveImageView.setImageResource(R.drawable.ic_baseline_liked)
            else loveImageView.setImageResource(R.drawable.ic_baseline_like)
        }
            binding.loveImageView.setOnClickListener {
                viewModel.likes()
            }
            binding.repostImageView.setOnClickListener {
                viewModel.reposts()
            }
            binding.seeImageView.setOnClickListener {
                viewModel.sees()
            }


    }

    }
}

 fun Calculate(x : Int):String {
        var y: String =""
        var z1: String
        var z2: String
        var xString: String

        xString = x.toString()
        if (x < 1000) { y=x.toString() }

        if ((x>=1000) && (x<1100))
        {
          z1 = xString.dropLast( 3)
          y= z1 +"K"
        }
        if ((x>=1100) && (x<20000))
        {
            z1 = xString.dropLast( 3)
            z2 = xString.dropLast( 2)
            z2 = z2.reversed()
            z2 = z2[0].toString()
            y= z1 + "."+ z2 +"K"
        }

        if ((x>=20000) && (x< 1000000))
        {
            z1 = xString.dropLast( 3)
            y= z1 + "K"
        }

        if (x>=1000000) {
            z1 = xString.dropLast(6)
            z2 = xString.dropLast(5)
            z2 = z2.reversed()
            z2 = z2[0].toString()
            y = z1 + "." + z2 + "M"
        }
         return y
    }
