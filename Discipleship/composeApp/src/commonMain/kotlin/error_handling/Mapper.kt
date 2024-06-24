package error_handling

import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.utils.io.errors.IOException

fun Throwable.toNetworkError(): NetworkError {
    val error = when(this) {
        is IOException -> Error.NetworkError
        is HttpRequestTimeoutException -> Error.NetworkError
        else -> Error.UnknownError
    }
    return NetworkError(error, this)
}