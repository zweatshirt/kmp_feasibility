package realm.domain.model

import io.realm.kotlin.types.RealmObject;
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId;
class DiscipleEntity : RealmObject {
    @PrimaryKey
    var _id: String = ""

    var mentorId: String? = null

    var userId: String? = null
}
