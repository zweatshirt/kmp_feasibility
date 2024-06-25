package home.domain.repository

import arrow.core.Either
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import error_handling.NetworkError
import home.domain.model.Tool

interface ToolsRepository {
    @GET("resources/")
    suspend fun getTools(): Either<NetworkError, List<Tool>>
}