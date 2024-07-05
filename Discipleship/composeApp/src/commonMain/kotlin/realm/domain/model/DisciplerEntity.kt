package realm.domain.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class DisciplerEntity: RealmObject {
    @PrimaryKey
    var id: String = ""
    var userId: String = ""
    var disciples: RealmList<String> = realmListOf()
}