package realm.domain.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject;
import io.realm.kotlin.types.annotations.PrimaryKey

class DiscipleEntity : RealmObject {
    @PrimaryKey
    var _id: String = ""

    var mentorId: String? = null

    var userId: String? = null

    var toolIds: RealmList<String> = realmListOf()
}
