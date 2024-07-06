package realm.domain.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId;

class DisciplerEntity : RealmObject {
    @PrimaryKey
    var _id: String = ""

    var disciples: RealmList<String> = realmListOf()

    var userId: String? = null
}

