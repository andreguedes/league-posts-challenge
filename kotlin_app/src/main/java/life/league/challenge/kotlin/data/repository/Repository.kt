package life.league.challenge.kotlin.data.repository

import life.league.challenge.kotlin.data.service.Service

class Repository(private val service: Service) {

    suspend fun login() = getService().login()

    suspend fun posts(account: String) = getService().posts(account)

    private fun getService() = service.getService()

}