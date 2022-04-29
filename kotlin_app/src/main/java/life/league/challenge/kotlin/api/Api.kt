package life.league.challenge.kotlin.api

import life.league.challenge.kotlin.model.Account
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Retrofit API interface definition using coroutines. Feel free to change this implementation to
 * suit your chosen architecture pattern and concurrency tools
 */
interface Api {

    @GET("login")
    suspend fun login(@Header("Authorization") credentials: String?): Account

}

/**
 * Overloaded Login API extension function to handle authorization header encoding
 */
suspend fun Api.login(username: String, password: String)
        = login("Basic " + android.util.Base64.encodeToString("$username:$password".toByteArray(), android.util.Base64.NO_WRAP))