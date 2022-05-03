package life.league.challenge.kotlin.data.model

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("api_key") val apiKey: String? = null
)
