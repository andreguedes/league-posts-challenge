package life.league.challenge.kotlin.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.api.Service
import life.league.challenge.kotlin.api.login

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        // example api call to login, feel free to delete this and implement the call to login
        // somewhere else differently depending on your chosen architecture
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val account = Service.api.login("hello", "world")
                Log.v(TAG, account.apiKey ?: "")
            } catch (t : Throwable) {
                Log.e(TAG, t.message, t)
            }
        }
    }

}
