package profile.data.user

import org.jetbrains.compose.resources.DrawableResource

class Discipler(
    firstName: String,
    lastName: String,
    email: String,
    image: DrawableResource?,
    bio: String):
        User(firstName, lastName, email, image, bio)

