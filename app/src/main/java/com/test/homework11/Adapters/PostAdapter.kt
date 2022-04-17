package com.test.homework11.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.homework11.Models.Post
import com.test.homework11.R

class PostAdapter(private val items: MutableList<Post>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val rootView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.post_item, parent, false)

        return PostViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textNameView: TextView = itemView.findViewById(R.id.post)

    fun bind(post: Post) {
        textNameView.text = post.name
    }
}