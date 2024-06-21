package viewmodel

import dev.gitlive.firebase.auth.FirebaseUser

// the one thing we want to persist across screens is the firebase user for authentication
data class ScreenData(
    var isDisciple: Boolean,
    var currentUser: FirebaseUser?
    )