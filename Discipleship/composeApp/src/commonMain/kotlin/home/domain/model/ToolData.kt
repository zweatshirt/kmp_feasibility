package home.domain.model


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

/* Author: Zach */
// This ToolData class is meant to serve as a container
// for any GodTools tools/data we collect from the API
// chances are it will need to be changed significantly
// It all gets converted into a Tool object, which contains significantly less data
// The Tool object is what we actually want to display in app.
// The extra data from ToolData may prove useful in the future, though.

@Serializable
data class ToolData(
    val data: List<ToolJSON>
)

@Serializable
data class ToolJSON (
    val id: String,
    val type: String,
    val attributes: ResourceAttributes,
    val relationships: ResourceRelationships?
)

@Serializable
data class ResourceAttributes @OptIn(ExperimentalSerializationApi::class) constructor(
    val name: String,
    val abbreviation: String,
    val description: String? = null,
    @JsonNames("onesky-project-id")
    val oneskyProjectId: Int? = null,
    val manifest: String? = null,
    @JsonNames("resource-type")
    val resourceType: String? = null,
    @JsonNames("total-views")
    val totalViews: Int? = null,
    @JsonNames("attribute-category")
    val attrCategory: String? = null,
    @JsonNames("attribute-banner")
    val attrBanner: String? = null,
    @JsonNames("attribute-banner-about")
    val attrBannerAbout: String? = null
)

@Serializable
data class ResourceRelationships @OptIn(ExperimentalSerializationApi::class) constructor(
    val system: RelationshipItem? = null,
    val metatool: RelationshipItem? = null,
    @JsonNames("latest-translations")
    val latestTranslations: LatestTranslations? = null,
    @JsonNames("latest-drafts-translations")
    val latestDraftsTranslations: LatestDraftsTranslations? = null,
    val pages: Pages? = null,
    val tips: Tips? = null, // Adjust the type as needed based on actual data
    val attachments: Attachments? = null,
    @JsonNames("custom-manifests")
    val customManifests: CustomManifests? = null, // Adjust the type as needed based on actual data
    @JsonNames("translated-attributes")
    val translatedAttributes: TranslatedAttributes? = null // Adjust the type as needed based on actual data
)

@Serializable
data class TranslatedAttributes (
    val data: List<DataItem>? = null
)

@Serializable
data class LatestTranslations(
    val data: List<DataItem>? = null
)

@Serializable
data class CustomManifests(
    val data: List<DataItem>? = null
)

@Serializable
data class LatestDraftsTranslations(
    val data: List<DataItem>? = null
)

@Serializable
data class Attachments(
    val data: List<AttachmentItem>? = null
)

@Serializable
data class Pages(
    val data: List<PageItem>? = null
)

@Serializable
data class Tips(
    val data: List<DataItem>? = null
)


@Serializable
data class RelationshipItem(
    val data: DataItem? = null
)

@Serializable
data class DataItem(
    val id: String? = null,
    val type: String? = null
)

@Serializable
data class PageItem(
    val id: String? = null,
    val type: String? = null
)

@Serializable
data class AttachmentItem(
    val id: String? = null,
    val type: String? = null
)
