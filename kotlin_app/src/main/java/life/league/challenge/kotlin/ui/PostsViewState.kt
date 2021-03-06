package life.league.challenge.kotlin.ui

import life.league.challenge.kotlin.data.model.ui.PostsUIModel

sealed class PostsViewState {

    data class LoginSuccess(val account: String) : PostsViewState()
    data class PostsSuccess(val posts: List<PostsUIModel>) : PostsViewState()
    object Error : PostsViewState()

}
