package life.league.challenge.kotlin.data.remote

import life.league.challenge.kotlin.data.model.Account
import retrofit2.http.GET

interface Api {

    @GET("login")
    suspend fun login(): Account

}
