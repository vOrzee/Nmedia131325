package ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
           id = 1,
           author = "Нетология. Университет интернет профессий будущего !",
           content = "К нам приходят и начинающие специалисты, и руководители крупных компаний. Всех объединяет одно — желание добиться большего, чем есть сейчас.Мы даём те знания и навыки, которые помогают реализовать себя в профессии, больше зарабатывать, оптимизировать рутину и заниматься более сложными, но интересными задачами.  ",
           published = "08 апреля 2023 года ",
           likeByMe = false,
           likes =11,
           repost = 1499999,
           see = 1099
        )


        with(binding){
        authorTextView.text = post.author
        dateTextView.text = post.published
        aboutTextView.text = post.content
        loveTextView.text = Calculate(post.likes)
        repostTextView.text = Calculate(post.repost)
        seeTextView.text = Calculate(post.see)
        if (post.likeByMe) {
          loveImageView.setImageResource(R.drawable.ic_baseline_liked)
        }
        binding.loveImageView.setOnClickListener{
            post.likeByMe = !post.likeByMe

            binding.loveImageView.setImageResource(
                if (post.likeByMe) R.drawable.ic_baseline_liked
                else R.drawable.ic_baseline_like
            )
            if (post.likeByMe) post.likes++ else post.likes--
            loveTextView.text = Calculate(post.likes)
        }
        binding.repostImageView.setOnClickListener{
            post.repost++
            repostTextView.text = Calculate(post.repost)
        }
        binding.seeImageView.setOnClickListener{
            post.see++
            seeTextView.text = Calculate(post.see)
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
