package life.league.challenge.kotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.kotlin.data.model.Post
import life.league.challenge.kotlin.databinding.ItemRecyclerviewPostBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding =
            ItemRecyclerviewPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    fun updatePosts(posts: List<Post>) {
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    inner class PostsViewHolder(private val binding: ItemRecyclerviewPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            with(binding) {
                postTitle.text = post.title
                postBody.text = post.body
            }
        }

    }
}
