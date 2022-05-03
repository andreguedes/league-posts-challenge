package life.league.challenge.kotlin

import life.league.challenge.kotlin.data.model.Account
import life.league.challenge.kotlin.data.model.Post

object PostsMock {

    fun getAccount() = Account("IUQYE28763286bAJKHHJKAKS189720")

    fun getPosts() = listOf(
        Post(
            2,
            19,
            "adipisci placeat illum aut reiciendis qui",
            "illum quis cupiditate provident sit magnam\\nea sed aut omnis\\nveniam maiores ullam consequatur atque\\nadipisci quo iste expedita sit quos voluptas"
        ),
        Post(
            10,
            97,
            "quas fugiat ut perspiciatis vero provident",
            "eum non blanditiis soluta porro quibusdam voluptas\\nvel voluptatem qui placeat dolores qui velit aut\\nvel inventore aut cumque culpa explicabo aliquid at\\nperspiciatis est et voluptatem dignissimos dolor itaque sit nam"
        ),
        Post(
            6,
            51,
            "soluta aliquam aperiam consequatur illo quis voluptas",
            "sunt dolores aut doloribus\\ndolore doloribus voluptates tempora et\\ndoloremque et quo\\ncum asperiores sit consectetur dolorem"
        )
    )

}