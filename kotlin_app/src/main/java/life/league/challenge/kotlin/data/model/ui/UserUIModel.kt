package life.league.challenge.kotlin.data.model.ui

import com.google.gson.annotations.SerializedName

data class UserUIModel(
    @SerializedName("name") val name: String,
    @SerializedName("thumbnail") val thumbnailUrl: String
)
