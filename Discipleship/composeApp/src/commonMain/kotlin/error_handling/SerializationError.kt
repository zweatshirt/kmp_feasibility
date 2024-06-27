package error_handling

class SerializationError (
    val error: Error,
    val t: Throwable? = null
)