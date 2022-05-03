package life.league.challenge.kotlin.data.repository

import life.league.challenge.kotlin.data.service.Service

class Repository(private val service: Service) {

    suspend fun login() = getService().login()

    suspend fun posts(account: String) = getService().posts(account)

    suspend fun users(account: String) = getService().users(account)

    private fun getService() = service.getService()

}