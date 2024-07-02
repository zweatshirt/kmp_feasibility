package profile.domain.model

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

class RealmDisciple {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var userId: ObjectId = ObjectId()
    var mentorId: String = ""
}
