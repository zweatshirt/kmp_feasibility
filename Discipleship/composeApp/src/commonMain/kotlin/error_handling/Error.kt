package error_handling

enum class Error(val message: String) {
    NetworkError("Network Error"),
    UnknownError("Unknown Error"),
    SerializationError("Serialization Error")
}