package life.league.challenge.kotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.data.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val state = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState> = state

    private var isLoadingAccount: Boolean = true

    fun isLoadingAccount() = isLoadingAccount

    fun login() {
        viewModelScope.launch {
            state.value = try {
                val account = repository.login()
                isLoadingAccount = false
                MainViewState.Success(account.apiKey ?: "")
            } catch (t: Throwable) {
                MainViewState.Error(t.message, t)
            }
        }
    }

}
