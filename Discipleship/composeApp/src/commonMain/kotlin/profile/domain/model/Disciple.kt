package profile.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.jetbrains.compose.resources.DrawableResource
import org.mongodb.kbson.ObjectId

class Disciple(
    uID: String,
    firstName: String,
    lastName: String,
    email: String,
    image: DrawableResource?,
    bio: String):
    User(uID, firstName, lastName, email, image, bio)

class DiscipleEntity: RealmObject {
    @PrimaryKey
    var id: String = ""
    var userId: String = ""
}

