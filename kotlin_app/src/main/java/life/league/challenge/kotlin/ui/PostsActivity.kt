package life.league.challenge.kotlin.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.data.model.ui.PostsUIModel
import life.league.challenge.kotlin.databinding.ActivityPostsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding
    private val adapterPosts by lazy { PostsAdapter() }

    private val viewModel: PostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoadingAccount()
        }

        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.login()
    }

    override fun onResume() {
        super.onResume()

        binding.recyclerviewPosts.apply {
            layoutManager = LinearLayoutManager(this@PostsActivity)
            adapter = adapterPosts
            addItemDecoration(
                DividerItemDecoration(
                    this@PostsActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        viewModel.viewState.observe(this) {
            when (it) {
                is PostsViewState.LoginSuccess -> {
                    getPosts(it.account)
                }
                is PostsViewState.PostsSuccess -> {
                    updatePosts(it.posts)
                }
                is PostsViewState.Error -> {
                    showErrorMessage()
                }
            }
        }
    }

    private fun getPosts(account: String) {
        viewModel.postsFromUsers(account)
    }

    private fun updatePosts(posts: List<PostsUIModel>) {
        adapterPosts.updatePosts(posts)
    }

    private fun showErrorMessage() {
        Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
    }

}
