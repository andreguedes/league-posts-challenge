package life.league.challenge.kotlin.data.repository

import life.league.challenge.kotlin.data.service.Service

class Repository(private val service: Service) {

    suspend fun login() = service.getService().login()

}