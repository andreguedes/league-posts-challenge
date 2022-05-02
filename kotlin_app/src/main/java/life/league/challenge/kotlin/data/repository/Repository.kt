package life.league.challenge.kotlin.data.repository

import life.league.challenge.kotlin.data.remote.Service

class Repository {

    private val service by lazy { Service }

    suspend fun login() = service.api.login()

}