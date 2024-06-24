package home.domain.model

// This class is meant to serve as a container
// for any GodTools tools/data we collect from the API
// chances are it will need to be changed significantly
data class Tool (
    val toolName: String,
    val toolDescription: String,
    val toolLink: String, // will need to be converted into url/uri
    val languageList: List<String>?
)
