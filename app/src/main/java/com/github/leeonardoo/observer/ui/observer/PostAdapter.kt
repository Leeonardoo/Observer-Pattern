package com.github.leeonardoo.observer.ui.observer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.leeonardoo.observer.databinding.RowPostBinding
import com.github.leeonardoo.observer.model.Post

class PostAdapter(
    private val onClickRemove: (Post) -> Unit
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var posts: List<Post> = listOf()

    fun updateList(posts: List<Post>) {
        val diffCallback = PostDiffCallback(this.posts, posts)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.posts = posts.toList()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowPostBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = posts.size

    private fun getItem(position: Int): Post = posts[position]

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bindView(getItem(position))
        }
    }

    inner class ViewHolder(private val binding: RowPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(post: Post) {
            binding.post = post

            binding.deleteButton.setOnClickListener {
                onClickRemove(post)
            }

            binding.executePendingBindings()
        }
    }
}