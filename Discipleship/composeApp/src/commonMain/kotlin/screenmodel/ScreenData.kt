package screenmodel

import dev.gitlive.firebase.auth.FirebaseUser
import realm.data.remote.RealmApi

// the one thing we want to persist across screens is the firebase user for authentication
data class ScreenData(
    var isDisciple: Boolean,
    var currentUser: io.realm.kotlin.mongodb.User?,
    val realmApi: RealmApi?
    )