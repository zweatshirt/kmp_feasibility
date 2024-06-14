package discipler

import org.jetbrains.compose.resources.DrawableResource

data class Discipler (
    val name: String,
    val email: String,
    val image: DrawableResource?,
    val bio: String?
)