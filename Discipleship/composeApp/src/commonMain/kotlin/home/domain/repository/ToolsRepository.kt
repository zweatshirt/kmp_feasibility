package home.domain.repository

import arrow.core.Either
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import error_handling.NetworkError
import global_consts.Constants
import home.domain.model.Tool

interface ToolsRepository {
//    @GET("resources")
//    suspend fun getTool(
//        @Query("id") id: String
//    ): Either<NetworkError, Tool>
//
//    @GET(Constants.TOOLS_API)

    // For getting tools from external KnowingGod.com API
    suspend fun getTools(): Either<NetworkError, List<Tool>>

    suspend fun writeToolsToDb(tools: List<Tool>)

    // Create getter for individual tools from DB

}