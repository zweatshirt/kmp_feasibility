package home.data.remote

import co.touchlab.kermit.Logger
import home.domain.model.Tool
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse

// make call to GodTools/KnowGod.com API from here using Ktor lib
class ToolsApi {
    suspend fun getTools(): List<Tool> {
        val client = HttpClient()
        // make the urlString a constant soon
        val response: HttpResponse = client.request("https://mobile-content-api.cru.org/resources")
        val responseBody: String = response.body()
        Logger.i(responseBody)

        // This a dummy return... it's dumb I know... but just for now
        return listOf(Tool("w", "w", "w", listOf("w")))
    }
}