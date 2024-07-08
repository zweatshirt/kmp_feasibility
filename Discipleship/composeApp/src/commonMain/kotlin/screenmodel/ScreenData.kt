package screenmodel

import io.realm.kotlin.types.RealmObject

// the one thing we want to persist across screens is the firebase user for authentication
data class ScreenData(
    var userEntity: RealmObject?
    )