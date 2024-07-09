interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

annotation class CommonParcelize

expect interface MyParcelable

