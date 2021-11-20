package com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.models.Post

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.PostViewHolder>() {

    private var posts: MutableList<Post> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_list_item, null, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    fun setPosts(posts: MutableList<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    fun updatePost(post: Post) {
        posts[posts.indexOf(post)] = post
        notifyItemChanged(posts.indexOf(post))
    }

    class PostViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private var numComments:TextView = itemView.findViewById(R.id.num_comments)
        private var progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)

        fun bind(post: Post) {
            title.text = post.title

            if (post.comments.isEmpty()) {
                showProgressBar(true)
                numComments.text = ""
            } else {
                showProgressBar(false)
                numComments.text = post.comments.size.toString()
            }
        }

        private fun showProgressBar(showProgressBar: Boolean) {
            if (showProgressBar) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

}