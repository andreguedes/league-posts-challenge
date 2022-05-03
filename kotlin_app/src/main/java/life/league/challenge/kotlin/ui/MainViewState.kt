package life.league.challenge.kotlin.ui

import life.league.challenge.kotlin.data.model.Post

sealed class MainViewState {

    data class LoginSuccess(val account: String) : MainViewState()
    data class PostsSuccess(val posts: List<Post>) : MainViewState()

    data class Error(val message: String? = "Unknown error", val t: Throwable) : MainViewState()

}
