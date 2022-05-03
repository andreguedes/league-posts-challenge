package life.league.challenge.kotlin

import life.league.challenge.kotlin.data.model.response.AccountResponse
import life.league.challenge.kotlin.data.model.response.PostResponse
import life.league.challenge.kotlin.data.model.response.UserAvatarResponse
import life.league.challenge.kotlin.data.model.response.UserResponse
import life.league.challenge.kotlin.data.model.ui.PostsUIModel
import life.league.challenge.kotlin.data.model.ui.UserUIModel

object PostsMock {

    fun getAccount() = AccountResponse("IUQYE28763286bAJKHHJKAKS189720")

    fun getPostsFromUsers() = listOf(
        PostsUIModel(
            UserUIModel("Ervin Howell", "https://randomuser.me/api/portraits/thumb/women/38.jpg"),
            "adipisci placeat illum aut reiciendis qui",
            "illum quis cupiditate provident sit magnam\\nea sed aut omnis\\nveniam maiores ullam consequatur atque\\nadipisci quo iste expedita sit quos voluptas"
        ),
        PostsUIModel(
            UserUIModel("Leanne Graham", "https://randomuser.me/api/portraits/thumb/men/72.jpg"),
            "quas fugiat ut perspiciatis vero provident",
            "eum non blanditiis soluta porro quibusdam voluptas\\nvel voluptatem qui placeat dolores qui velit aut\\nvel inventore aut cumque culpa explicabo aliquid at\\nperspiciatis est et voluptatem dignissimos dolor itaque sit nam"
        ),
        PostsUIModel(
            UserUIModel("Victor Plains", "https://randomuser.me/api/portraits/thumb/women/39.jpg"),
            "soluta aliquam aperiam consequatur illo quis voluptas",
            "sunt dolores aut doloribus\\ndolore doloribus voluptates tempora et\\ndoloremque et quo\\ncum asperiores sit consectetur dolorem"
        )
    )

    fun getPosts() = listOf(
        PostResponse(
            2,
            19,
            "adipisci placeat illum aut reiciendis qui",
            "illum quis cupiditate provident sit magnam\\nea sed aut omnis\\nveniam maiores ullam consequatur atque\\nadipisci quo iste expedita sit quos voluptas"
        ),
        PostResponse(
            10,
            97,
            "quas fugiat ut perspiciatis vero provident",
            "eum non blanditiis soluta porro quibusdam voluptas\\nvel voluptatem qui placeat dolores qui velit aut\\nvel inventore aut cumque culpa explicabo aliquid at\\nperspiciatis est et voluptatem dignissimos dolor itaque sit nam"
        ),
        PostResponse(
            6,
            51,
            "soluta aliquam aperiam consequatur illo quis voluptas",
            "sunt dolores aut doloribus\\ndolore doloribus voluptates tempora et\\ndoloremque et quo\\ncum asperiores sit consectetur dolorem"
        )
    )

    fun getUsers() = listOf(
        UserResponse(
            2,
            UserAvatarResponse("https://randomuser.me/api/portraits/thumb/women/38.jpg"),
            "Ervin Howell"
        ),
        UserResponse(
            10,
            UserAvatarResponse("https://randomuser.me/api/portraits/thumb/men/72.jpg"),
            "Leanne Graham"
        ),
        UserResponse(
            6,
            UserAvatarResponse("https://randomuser.me/api/portraits/thumb/women/39.jpg"),
            "Victor Plains"
        ),
    )

}