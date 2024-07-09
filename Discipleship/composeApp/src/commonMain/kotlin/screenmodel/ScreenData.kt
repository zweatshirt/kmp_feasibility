package screenmodel

import CommonParcelize
import MyParcelable
import io.realm.kotlin.types.RealmObject
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmField

//import kotlinx.parcelize.Parcelize

// the one thing we want to persist across screens is the firebase user for authentication
@Serializable
@CommonParcelize
data class ScreenData(
    var userEntity: RealmObject?
): MyParcelable