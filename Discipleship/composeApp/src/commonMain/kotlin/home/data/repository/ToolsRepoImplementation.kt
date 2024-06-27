package home.data.repository

import arrow.core.Either
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

    // Needs proper exception handling.
    override suspend fun writeToolsToDb(tools: List<Tool>) {
        toolsApi.writeToolsToDb(tools)
    }

    override suspend fun writeToolToDb(tool: Tool) {
        toolsApi.writeToolToDb(tool)
    }

    override  suspend fun readToolsFromDb(): Either<NetworkError, List<Tool>> {
        return Either.catch {
            toolsApi.readToolsFromDb()
        }.mapLeft { it.toNetworkError() }
    }

}