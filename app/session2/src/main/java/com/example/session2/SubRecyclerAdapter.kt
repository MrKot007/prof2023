package com.example.session2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session2.databinding.SmallTagBinding
import com.example.session2.databinding.TagBinding

class SmallTagViewHolder(var binding: SmallTagBinding) : RecyclerView.ViewHolder(binding.root)
class SubRecyclerAdapter(val tagList: List<ModelTag>, val tags: List<Int>) : RecyclerView.Adapter<SmallTagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallTagViewHolder {
        return SmallTagViewHolder(SmallTagBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SmallTagViewHolder, position: Int) {
        for (i in tagList) {
            if (i.id == tags[position]) {
                holder.binding.smallTagText.text = i.name
                break
            }
        }
    }

    override fun getItemCount(): Int {
        return tags.size
    }
}