package profile.domain.model

import io.realm.kotlin.types.annotations.PrimaryKey
import org.jetbrains.compose.resources.DrawableResource
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class Discipler(
    uID: String,
    firstName: String,
    lastName: String,
    email: String,
    image: DrawableResource?,
    bio: String):
        User(uID, firstName, lastName, email, image, bio
        )

class RealmDiscipler {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var userId: ObjectId = ObjectId()
    var disciples: List<ObjectId>? = null
}