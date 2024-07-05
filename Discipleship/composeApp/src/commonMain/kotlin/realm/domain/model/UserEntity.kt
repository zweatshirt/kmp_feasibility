package realm.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

open class UserEntity : RealmObject {
//    @PrimaryKey
    var id: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
//    var image: RealmList<Byte> = realmListOf()
    var bio: String? = null
}