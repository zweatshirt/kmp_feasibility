package home.data.remote

import co.touchlab.kermit.Logger
import global_consts.Constants
import home.domain.model.Tool
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse

/* Author: Zach */
// make call to GodTools/KnowGod.com API from here using Ktor lib
class ToolsApi {

    suspend fun getTools(): List<Tool> {
        val client = HttpClient()
        // Switch the constant and any other string constants to Res.value.string vars soon
        val response: HttpResponse = client.request(Constants.TOOLS_API)
        val responseBody: String = response.body()
        Logger.i(responseBody)

        // Only take objects with attribute resource-type: "tract"
        // the link to the tool will be like this:
        // https://knowgod.com/en/kgp/0
        // where 'kgp' is the abbreviation attribute of the JSON object



        // This a dummy return... it's dumb I know... but just for now
        return listOf(Tool(109,"w", "w", "w", "100"))
    }
}