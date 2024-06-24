package home.domain.repository

import arrow.core.Either
import error_handling.NetworkError
import home.domain.model.Tool

interface ToolsRepository {
    suspend fun getTools(): Either<NetworkError, List<Tool>>
}