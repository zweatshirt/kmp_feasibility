package realm.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class DiscipleEntity: RealmObject {
    @PrimaryKey var id: String = ""
    var userId: String = ""
}