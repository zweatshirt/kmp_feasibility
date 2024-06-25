package home.data.remote

import co.touchlab.kermit.Logger
import de.jensklingenberg.ktorfit.Ktorfit
import global_consts.Constants
import home.domain.model.Tool
import home.domain.model.ToolData
import home.domain.model.ToolJSON
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString
import kotlinx.serialization.json.decodeFromJsonElement

/* Author: Zach */
// make call to GodTools/KnowGod.com API from here using Ktor lib
class ToolsApi {

    suspend fun getTools(): List<Tool> {
        val client = HttpClient(CIO) // change to different engines for iOS and Android
        // make the urlString a constant soon
//        val ktorfit = Ktorfit.Builder().baseUrl(Constants.TOOLS_API).build()


        Logger.i("In ToolsApi.getTools()\nREQUEST TO: ${Constants.TOOLS_API}")
        val response: HttpResponse = client.request(Constants.TOOLS_API)
        val responseBody: String = response.body()
        Logger.i("RESPONSE BODY: $responseBody")

        val tools: ToolData
        val toolsObjs = mutableListOf<Tool>()
        // Switch the constant and any other string constants to Res.value.string vars soon
        try {
            val json = Json {
                ignoreUnknownKeys = true
            }
            tools = json.decodeFromString(responseBody)
            Logger.i("DATA: $tools")
            tools.data.forEach { tool ->
                val toolLink = "https://knowgod.com/en/${tool.attributes?.abbreviation}/0"
                if (tool.type == "tract") {
                    toolsObjs.add(
                        Tool(
                            tool.id,
                            tool.attributes.name,
                            tool.attributes.description,
                            toolLink,
                            tool.attributes.totalViews
                        )
                    )
                }
                Logger.i(toolsObjs.toString())
            }
        }
        catch (e: IllegalArgumentException) {
            Logger.e("$e: Failed to grab data from the response body")
        }
        catch (e: SerializationException) {
            Logger.e("$e")
        }



        // Only take objects with attribute resource-type: "tract"
        // the link to the tool will be like this:
        // https://knowgod.com/en/kgp/0
        // where 'kgp' is the abbreviation attribute of the JSON object



        // This a dummy return... it's dumb I know... but just for now
//        return listOf(Tool(109,"w", "w", "w", "100"))
        return toolsObjs
    }
}