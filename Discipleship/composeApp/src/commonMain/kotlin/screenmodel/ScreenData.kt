package screenmodel

import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.serialization.Serializable
import realm.data.remote.RealmApi

// the one thing we want to persist across screens is the firebase user for authentication
@Serializable
data class ScreenData(
    var isDisciple: Boolean,
    var currentUser: io.realm.kotlin.mongodb.User?,
    )