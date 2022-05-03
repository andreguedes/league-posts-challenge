package life.league.challenge.kotlin.ui

import life.league.challenge.kotlin.data.model.ui.PostsUIModel

sealed class MainViewState {

    data class LoginSuccess(val account: String) : MainViewState()
    data class PostsSuccess(val posts: List<PostsUIModel>) : MainViewState()
    object Error : MainViewState()

}
