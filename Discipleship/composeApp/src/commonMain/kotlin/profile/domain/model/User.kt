package profile.domain.model

import androidx.compose.ui.graphics.ImageBitmap
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import org.jetbrains.compose.resources.DrawableResource
import org.mongodb.kbson.ObjectId

open class User(
    val uID: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val image: DrawableResource? = null,
    val bio: String? = null
)

open class UserEntity : RealmObject {
    var id: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    var image: RealmList<Byte> = realmListOf()
    var bio: String? = null
}
