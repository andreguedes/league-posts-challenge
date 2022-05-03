package life.league.challenge.kotlin.data.remote

import life.league.challenge.kotlin.data.model.Account
import life.league.challenge.kotlin.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Header

interface Api {

    @GET("login")
    suspend fun login(): Account

    @GET("posts")
    suspend fun posts(@Header("x-access-token") credentials: String?): List<Post>

}
