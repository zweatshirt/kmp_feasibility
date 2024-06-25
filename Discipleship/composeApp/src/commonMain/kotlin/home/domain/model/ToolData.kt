package home.domain.model


import kotlinx.serialization.Serializable

//import kotlinx.serialization.Serializable
// This class is meant to serve as a container
// for any GodTools tools/data we collect from the API
// chances are it will need to be changed significantly

@Serializable
data class ToolData(
    val data: List<ToolJSON>
)

@Serializable
data class ToolJSON(
    val id: String,
    val type: String,
    val attributes: ResourceAttributes,
    val relationships: ResourceRelationships?
)

@Serializable
data class ResourceAttributes(
    val name: String,
    val abbreviation: String,
    val description: String? = null,
    val oneskyProjectId: Int? = null,
    val manifest: String? = null,
    val resourceType: String? = null,
    val totalViews: Int? = null,
    val attrCategory: String? = null,
    val attrBanner: String? = null,
    val attrBannerAbout: String? = null
)

@Serializable
data class ResourceRelationships(
    val system: RelationshipItem?,
    val metatool: RelationshipItem?,
    val latestTranslations: List<TranslationItem>?,
    val latestDraftsTranslations: List<TranslationItem>?,
    val pages: Pages?,
    val tips: Tips, // Adjust the type as needed based on actual data
    val attachments: Attachments,
    val customManifests: List<String>?, // Adjust the type as needed based on actual data
    val translatedAttributes: List<String>? // Adjust the type as needed based on actual data
)

@Serializable
data class Attachments(
    val data: List<AttachmentItem>?
)

@Serializable
data class Pages(
    val data: List<PageItem>?
)

@Serializable
data class Tips(
    val data: List<String>?
)

@Serializable
data class RelationshipItem(
    val data: DataItem?
)

@Serializable
data class DataItem(
    val id: String?,
    val type: String?
)

@Serializable
data class TranslationItem(
    val id: String?,
    val type: String?
)

@Serializable
data class PageItem(
    val id: String?,
    val type: String?
)

@Serializable
data class AttachmentItem(
    val id: String?,
    val type: String?
)
