package life.league.challenge.kotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import life.league.challenge.kotlin.data.model.ui.PostsUIModel
import life.league.challenge.kotlin.data.model.ui.UserUIModel
import life.league.challenge.kotlin.data.repository.Repository

class PostsViewModel(private val repository: Repository) : ViewModel() {

    private val state = MutableLiveData<PostsViewState>()
    val viewState: LiveData<PostsViewState> = state

    private var isLoadingAccount: Boolean = true

    fun isLoadingAccount() = isLoadingAccount

    fun login() {
        viewModelScope.launch {
            state.value = try {
                val account = repository.login()
                if (account.isSuccessful) {
                    isLoadingAccount = false
                    val accountApiKey = account.body()?.apiKey
                    PostsViewState.LoginSuccess(accountApiKey ?: "")
                } else PostsViewState.Error
            } catch (t: Throwable) {
                PostsViewState.Error
            }
        }
    }

    fun postsFromUsers(account: String) {
        viewModelScope.launch {
            state.value = try {
                val postsFromUsers = mutableListOf<PostsUIModel>()

                val postsResponse =
                    withContext(Dispatchers.Default) {
                        repository.posts(account)
                    }
                val usersResponse =
                    withContext(Dispatchers.Default) {
                        repository.users(account)
                    }

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
                    PostsViewState.PostsSuccess(postsFromUsers)
                } else PostsViewState.Error
            } catch (t: Throwable) {
                PostsViewState.Error
            }
        }
    }
}
