package realm.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import org.mongodb.kbson.BsonObjectId

class UserEntity : RealmObject {
    @PrimaryKey
    var _id: String = ""

    var bio: String? = null

    var email: String? = null

    var firstName: String? = null

    var lastName: String? = null

    var isDisciple: Boolean? = null
}
