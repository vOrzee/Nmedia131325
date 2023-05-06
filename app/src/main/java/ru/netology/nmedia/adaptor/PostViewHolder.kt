package ru.netology.nmedia.adaptor

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Calculate
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val listener: PostListener,
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
                listener.onLike(post)
            }
           binding.repostImageView.setOnClickListener {
               listener.onView(post)
           }
           binding.seeImageView.setOnClickListener {
               listener.onSee(post)
           }
           binding.menu.setOnClickListener{
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_options)
                    setOnMenuItemClickListener { item ->
                        when(item.itemId){
                            R.id.remove -> {
                                listener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                listener.onEdit(post)
                                true
                            }
                            R.id.cancel -> {
                                listener.onCancel(post)
                                true
                            }
                        else -> false
                        }
                    }
                }
                    .show()
           }

        }

    }
}