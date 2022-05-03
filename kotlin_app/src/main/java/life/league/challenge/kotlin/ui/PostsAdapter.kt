package life.league.challenge.kotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.kotlin.data.model.ui.PostsUIModel
import life.league.challenge.kotlin.databinding.ItemRecyclerviewPostBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val posts = mutableListOf<PostsUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding =
            ItemRecyclerviewPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    fun updatePosts(posts: List<PostsUIModel>) {
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    inner class PostsViewHolder(private val binding: ItemRecyclerviewPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostsUIModel) {
            with(binding) {
                postTitle.text = post.title
                postBody.text = post.body
            }
        }

    }
}
