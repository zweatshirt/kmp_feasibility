package acct_creation.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.launch

/* Author: Zachery Linscott
* This class handles user authentication
* We also want the class to populate the User object with backend user data
* We may use Firebase for this.
* We should try to typecast the User to a Disciple or Discipler object

* */
class LoginScreenViewModel: ViewModel() {
    var auth = Firebase.auth
    val scope = viewModelScope
    var firebaseUser: FirebaseUser? = null
    var userSignedIn = false
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    fun updateEmail(input: String) {
        email = input
    }
    fun updatePassword(input: String) {
        password = input
    }

    fun firebaseAuth(): FirebaseUser? {
        var currentUser = auth.currentUser
        scope.launch {
            try {
                // this is fine for now but it needs to go to the signup page soon instead
                auth.signInWithEmailAndPassword(email, password)
                Logger.i("Made it to login try")
                Logger.i("Value of firebase user ${auth.currentUser}")
            }
            catch(e: Exception) {
                // eventually want to populate the UI with a Snackbar indicating inability to login
//                auth.createUserWithEmailAndPassword(email, password)
                Logger.e("Exception found in firebaseAuth, likely user doesn't exist")
            }
        }
        Logger.i("currentUser value: $currentUser")
        return currentUser
    }
}