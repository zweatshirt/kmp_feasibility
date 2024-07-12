package home.data.remote

import co.touchlab.kermit.Logger
import global_consts.Constants
import home.domain.model.Tool
import home.domain.model.ToolData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.json.Json

/* Author: Zach */
// make call to GodTools/KnowGod.com API from here using Ktor lib
class ToolsApi {
    private val client = HttpClient()

    // Request to KnowingGod.com API
    suspend fun getTools(): List<Tool> {
        // make the urlString a constant soon
        Logger.i("In ToolsApi.getTools()\nREQUEST TO: ${Constants.TOOLS_API}")
        val response: HttpResponse = client.request(Constants.TOOLS_API) // get
        val responseBody: String = response.body()
        val json = Json { ignoreUnknownKeys = true }
        val tools: ToolData = json.decodeFromString(responseBody) // Response String to JSON
        return toolDataToObjects(tools) // converts JSON object to needed Tool objects
    }

    // Helper function to convert ToolData object (modeled after the API JSON) to Tool objects list
    private fun toolDataToObjects(tools: ToolData): List<Tool> {
        val toolsObjs = mutableListOf<Tool>()
        tools.data.forEach { tool ->
            val resType = tool.attributes.resourceType
            if (resType.equals("tract") || resType.equals("lesson")) {
                val toolLink = "https://knowgod.com/en/${tool.attributes.abbreviation}/0"
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
        }
        return toolsObjs
    }

    fun writeToolsToDb(tools: List<Tool>) {
        tools.forEach() { tool ->
            writeToolToDb(tool)
        }
    }

    fun writeToolToDb(tool: Tool) {
//        db.child(Constants.DB_TOOLS_NAME).child(tool.id).setValue(tool)
    }

    // Create read tools from DB function
    fun readToolsFromDb(): List<Tool> {
//        val toolsRef = db.child(Constants.DB_TOOLS_NAME)
        return listOf(Tool("2", "2", "2", "2", 2))
    }

}