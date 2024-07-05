package profile.domain.model

import org.jetbrains.compose.resources.DrawableResource

class Discipler(
    uID: String,
    firstName: String,
    lastName: String,
    email: String,
    image: DrawableResource?,
    bio: String):
        User(uID, firstName, lastName, email, image, bio)
