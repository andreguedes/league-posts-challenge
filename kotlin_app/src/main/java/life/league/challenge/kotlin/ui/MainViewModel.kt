package life.league.challenge.kotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.data.remote.Service
import life.league.challenge.kotlin.data.remote.login

class MainViewModel : ViewModel() {

    private val state = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState> = state

    fun login() {
        viewModelScope.launch {
            state.value = try {
                val account = Service.api.login("hello", "world")
                MainViewState.Success(account.apiKey ?: "")
            } catch (t: Throwable) {
                MainViewState.Error(t.message, t)
            }
        }
    }

}
