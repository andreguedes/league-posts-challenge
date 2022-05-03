package life.league.challenge.kotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.data.model.ui.PostsUIModel
import life.league.challenge.kotlin.data.model.ui.UserUIModel
import life.league.challenge.kotlin.data.repository.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val state = MutableLiveData<MainViewState>()
    val viewState: LiveData<MainViewState> = state

    private var isLoadingAccount: Boolean = true

    fun isLoadingAccount() = isLoadingAccount

    fun login() {
        viewModelScope.launch {
            state.value = try {
                var accountApiKey: String? = null
                val account = repository.login()
                if (account.isSuccessful) {
                    isLoadingAccount = false
                    accountApiKey = account.body()?.apiKey
                    MainViewState.LoginSuccess(accountApiKey ?: "")
                } else MainViewState.Error
            } catch (t: Throwable) {
                MainViewState.Error
            }
        }
    }

    fun postsFromUsers(account: String) {
        viewModelScope.launch {
            state.value = try {
                val postsFromUsers = mutableListOf<PostsUIModel>()

                val postsResponse = async { repository.posts(account) }.await()
                val usersResponse = async { repository.users(account) }.await()

                if (postsResponse.isSuccessful && usersResponse.isSuccessful) {
                    postsResponse.body().let { posts ->
                        posts?.map { post ->
                            usersResponse.body().let { users ->
                                users?.firstOrNull { user -> user.id == post.userId }?.let { user ->
                                    val userUIModel =
                                        UserUIModel(user.name, user.avatar.thumbnailUrl)
                                    postsFromUsers.add(
                                        PostsUIModel(userUIModel, post.title, post.body)
                                    )
                                }
                            }
                        }
                    }
                    MainViewState.PostsSuccess(postsFromUsers)
                } else MainViewState.Error
            } catch (t: Throwable) {
                MainViewState.Error
            }
        }
    }
}
