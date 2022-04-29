package life.league.challenge.kotlin.data.repository

import life.league.challenge.kotlin.data.remote.Service
import life.league.challenge.kotlin.data.remote.login

class Repository {

    private val service by lazy { Service }

    suspend fun login(username: String, password: String) =
        service.api.login(username, password)

}