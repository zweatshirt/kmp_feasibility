// This class is meant to serve as a container
// for any GodTools tools we collect from the API
// not sure
data class Tool (
    val toolName: String,
    val toolDescription: String,
    val toolLink: String, // will need to be converted into url/uri
    val languageList: List<String>?
)
