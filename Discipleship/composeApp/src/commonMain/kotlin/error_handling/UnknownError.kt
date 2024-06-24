package error_handling

class UnknownError (
    val error: Error,
    val t: Throwable? = null
)