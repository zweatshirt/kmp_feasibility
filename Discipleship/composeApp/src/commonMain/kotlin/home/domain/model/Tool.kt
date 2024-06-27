package home.domain.model

import kotlinx.serialization.Serializable

//import kotlinx.serialization.Serializable
// This class is meant to serve as a container
// for any GodTools tools/data we collect from the API
// chances are it will need to be changed significantly

@Serializable
data class Tool(
    val id: String,
    val name: String,
    val description: String?,
    val toolLink: String,
    val totalViews: Int?
)

