package home.domain.repository

import arrow.core.Either
import error_handling.NetworkError
import home.domain.model.Tool

interface ToolsRepository {

    // For getting tools from external KnowingGod.com API
    suspend fun getTools(): Either<NetworkError, List<Tool>>

    suspend fun writeToolsToDb(tools: List<Tool>)

    // Create getter for individual tools from DB
    // suspend fun readToolsFromDb(): List<Tool> {}

    suspend fun writeToolToDb(tool: Tool)

    suspend fun readToolsFromDb(): Either<NetworkError, List<Tool>>
}