package error_handling

import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

fun Throwable.toNetworkError(): NetworkError {
    val error = when(this) {
        is IOException -> Error.NetworkError
        is HttpRequestTimeoutException -> Error.NetworkError
        is SerializationException -> Error.NetworkError
        else -> Error.UnknownError
    }
    return NetworkError(error, this)
}