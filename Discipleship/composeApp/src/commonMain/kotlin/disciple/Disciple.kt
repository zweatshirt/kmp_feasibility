package disciple

import org.jetbrains.compose.resources.DrawableResource


data class Disciple (
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: DrawableResource?,
    val bio: String?
) {

}