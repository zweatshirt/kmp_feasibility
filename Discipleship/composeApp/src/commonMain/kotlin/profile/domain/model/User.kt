package profile.domain.model

import org.jetbrains.compose.resources.DrawableResource

open class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: DrawableResource?,
    val bio: String?
) {

}
