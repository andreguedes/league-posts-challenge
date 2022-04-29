package life.league.challenge.kotlin.data.remote

import life.league.challenge.kotlin.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Service {

    private const val TAG = "Service"

    val api: Api by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(Api::class.java)
    }
}
