package life.league.challenge.kotlin.data.model.response

import com.google.gson.annotations.SerializedName

data class UserAvatarResponse(
    @SerializedName("thumbnail") val thumbnailUrl: String
)
