package profile.domain.model

import org.jetbrains.compose.resources.DrawableResource

class Disciple(
    firstName: String,
    lastName: String,
    email: String,
    image: DrawableResource?,
    bio: String):
    User(firstName, lastName, email, image, bio)
