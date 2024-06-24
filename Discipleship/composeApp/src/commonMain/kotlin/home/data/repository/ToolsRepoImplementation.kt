package home.data.repository

import arrow.core.Either
import co.touchlab.kermit.Logger
import discipleship.composeapp.generated.resources.Res
import error_handling.NetworkError
import error_handling.toNetworkError
import home.data.remote.ToolsApi
import home.domain.model.Tool
import home.domain.repository.ToolsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*


// actual implementation of Tools Repository interface
class ToolsRepoImplementation constructor(
    private val toolsApi: ToolsApi
): ToolsRepository {
    // requests and fetches data from the
    override suspend fun getTools(): Either<NetworkError, List<Tool>> {
        return Either.catch {
            toolsApi.getTools()
        }.mapLeft { it.toNetworkError() }
    }

}