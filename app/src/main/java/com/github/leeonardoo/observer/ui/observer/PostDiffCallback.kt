package com.github.leeonardoo.observer.ui.observer

import androidx.recyclerview.widget.DiffUtil
import com.github.leeonardoo.observer.model.Post

class PostDiffCallback(
    private var oldList: List<Post>, private var newList: List<Post>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPlate = oldList[oldItemPosition]
        val newPlate = newList[newItemPosition]
        return oldPlate == newPlate
    }
}