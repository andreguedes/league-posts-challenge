package life.league.challenge.kotlin.data.remote

import life.league.challenge.kotlin.data.model.response.AccountResponse
import life.league.challenge.kotlin.data.model.response.PostResponse
import life.league.challenge.kotlin.data.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface Api {

    @GET("login")
    suspend fun login(): Response<AccountResponse>

    @GET("posts")
    suspend fun posts(@Header("x-access-token") credentials: String?): Response<List<PostResponse>>

    @GET("users")
    suspend fun users(@Header("x-access-token") credentials: String?): Response<List<UserResponse>>

}
