package realm.domain.model

import io.realm.kotlin.types.RealmObject;
import io.realm.kotlin.types.annotations.PrimaryKey

class DiscipleEntity : RealmObject {
    @PrimaryKey
    var _id: String = ""

    var mentorId: String? = null

    var userId: String? = null
}
