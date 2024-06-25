package home.data.repository

import arrow.core.Either
import de.jensklingenberg.ktorfit.http.GET
import error_handling.NetworkError
import error_handling.toNetworkError
import home.data.remote.ToolsApi
import home.domain.model.Tool
import home.domain.repository.ToolsRepository

/* Author: Zach */
// actual implementation of Tools Repository interface
class ToolsRepoImplementation(
    private val toolsApi: ToolsApi
): ToolsRepository {
    // requests and fetches data from the
    override suspend fun getTools(): Either<NetworkError, List<Tool>> {
        return Either.catch {
            toolsApi.getTools()
        }.mapLeft { it.toNetworkError() }
    }

}