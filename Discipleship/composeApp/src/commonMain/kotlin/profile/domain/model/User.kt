package profile.domain.model

import org.jetbrains.compose.resources.DrawableResource

open class User(
    uID: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: DrawableResource? = null,
    val bio: String? = null
) {

}
