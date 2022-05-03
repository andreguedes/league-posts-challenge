package life.league.challenge.kotlin.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import life.league.challenge.kotlin.data.model.ui.PostsUIModel
import life.league.challenge.kotlin.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapterPosts by lazy { PostsAdapter() }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.isLoadingAccount()
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.login()
    }

    override fun onResume() {
        super.onResume()

        binding.recyclerviewPosts.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterPosts
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        viewModel.viewState.observe(this) {
            when (it) {
                is MainViewState.LoginSuccess -> {
                    getPosts(it.account)
                }
                is MainViewState.PostsSuccess -> {
                    updatePosts(it.posts)
                }
                is MainViewState.Error -> {
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
        Toast.makeText(this, "Unknown error", Toast.LENGTH_SHORT).show()
    }

}
