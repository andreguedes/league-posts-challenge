package life.league.challenge.kotlin.data.service

import life.league.challenge.kotlin.data.remote.RetrofitClient

class Service(private val retrofitClient: RetrofitClient) {

    fun getService() = retrofitClient.getClient()

}
