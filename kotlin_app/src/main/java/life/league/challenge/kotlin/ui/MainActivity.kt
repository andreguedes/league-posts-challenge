package life.league.challenge.kotlin.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import life.league.challenge.kotlin.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

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

        viewModel.viewState.observe(this) {
            when (it) {
                is MainViewState.LoginSuccess -> {
                    getPosts(it.account)
                }
                is MainViewState.PostsSuccess -> {
                    Log.v(TAG, it.posts.toString())
                }
                is MainViewState.Error -> {
                    showErrorMessage(it.t)
                }
            }
        }
    }

    private fun getPosts(account: String) {
        Log.v(TAG, account)
        viewModel.posts(account)
    }

    private fun showErrorMessage(t: Throwable) {
        Log.e(TAG, t.message, t)
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

}
