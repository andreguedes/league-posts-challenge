package life.league.challenge.kotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import life.league.challenge.kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        viewModel.login()
        viewModel.viewState.observe(this) {
            when (it) {
                is MainViewState.Success -> Log.v(TAG, it.account)
                is MainViewState.Error -> Log.e(TAG, it.message, it.t)
            }
        }
    }

}
