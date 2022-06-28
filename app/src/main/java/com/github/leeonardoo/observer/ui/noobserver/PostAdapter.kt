package com.github.leeonardoo.observer.ui.noobserver

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.github.leeonardoo.observer.databinding.RowPostBinding
import com.github.leeonardoo.observer.model.Post

class PostAdapter(
    context: Context,
    posts: List<Post>,
    private val onClickRemove: (Post) -> Unit
) : ArrayAdapter<Post>(context, 0, posts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val post = getItem(position)

        if (convertView != null) {
            val binding = DataBindingUtil.getBinding<RowPostBinding>(convertView)
            binding?.post = post

            binding?.deleteButton?.setOnClickListener {
                if (post != null)
                    onClickRemove(post)
            }

            binding?.executePendingBindings()
            return convertView
        } else {
            val binding = RowPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

            binding.post = post

            binding.deleteButton.setOnClickListener {
                if (post != null)
                    onClickRemove(post)
            }

            binding.executePendingBindings()
            return binding.root
        }
    }
}