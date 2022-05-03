package life.league.challenge.kotlin.data.model.ui

import com.google.gson.annotations.SerializedName

data class PostsUIModel(
    @SerializedName("user") val user: UserUIModel,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)
