package ru.netology.nmedia.adaptor

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Calculate
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: (Post) -> Unit,
    private val onShareListener: (Post) -> Unit,
    private val onViewListener: (Post) -> Unit
) : ViewHolder(binding.root) {
    fun bind(post:Post){
        with(binding) {
            authorTextView.text = post.author
            dateTextView.text = post.published
            aboutTextView.text = post.content
            loveTextView.text = Calculate(post.likes)
            repostTextView.text = Calculate(post.reposts)
            seeTextView.text = Calculate(post.sees)
            loveImageView.setImageResource(
            if (post.likedByMe) R.drawable.ic_baseline_liked
            else R.drawable.ic_baseline_like
            )
            binding.loveImageView.setOnClickListener {
                onLikeListener(post)
            }
           binding.repostImageView.setOnClickListener {
               onShareListener(post)
           }
           binding.seeImageView.setOnClickListener {
               onViewListener(post)
           }


        }

    }
}