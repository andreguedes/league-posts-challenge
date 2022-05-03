package life.league.challenge.kotlin.data.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("avatar") val avatar: UserAvatarResponse,
    @SerializedName("name") val name: String
)

data class UserAvatarResponse(
    @SerializedName("thumbnail") val thumbnailUrl: String
)
