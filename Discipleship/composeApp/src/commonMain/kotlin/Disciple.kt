import androidx.compose.ui.graphics.vector.ImageVector

data class Disciple (
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: ImageVector?,
    val bio: String?
)