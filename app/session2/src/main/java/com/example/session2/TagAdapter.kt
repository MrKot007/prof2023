package com.example.session2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session2.databinding.TagBinding

class TagViewHolder(var binding: TagBinding) : RecyclerView.ViewHolder(binding.root)
class TagAdapter(val list: List<ModelTag>, val onClickTag: OnClickTag) : RecyclerView.Adapter<TagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(TagBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.binding.tagName.text = list[position].name
        holder.binding.body.setOnClickListener {
            onClickTag.onClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
interface OnClickTag {
    fun onClick(tag: ModelTag)
}
