package life.league.challenge.kotlin.ui

sealed class MainViewState {

    data class Success(val account: String) : MainViewState()
    data class Error(val message: String? = "Unknown error", val t: Throwable) : MainViewState()

}
