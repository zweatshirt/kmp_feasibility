package error_handling

data class NetworkError (
    val error: Error,
    val t: Throwable? = null
)
