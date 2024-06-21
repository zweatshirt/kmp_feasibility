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
* This class handles user signup and authentication in that regard
* It should also instantiate the User object and populate our DB, their first name, last name,
* etc should be maintained, and the rest of the data such as their image will be null until
* they fill out our forms
* */

// TODO: CODE NEEDS TO BE WRITTEN TO POPULATE USER OBJECT :>
class SignupScreenViewModel: ViewModel() {
    var auth = Firebase.auth
    val scope = viewModelScope
//    var firebaseUser: FirebaseUser? = null
//    var userSignedIn = false
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")

    fun updateEmail(input: String) {
        email = input
    }
    fun updatePassword(input: String) {
        password = input
    }
    fun updateConfirmPassword(input: String) {
        confirmPassword = input
    }
    fun updateFirstName(input: String) {
        firstName = input
    }
    fun updateLastName(input: String) {
        lastName = input
    }

    fun passwordsMatch(): Boolean {
        return password == confirmPassword
    }

    fun isPassMinLength(): Boolean {
        return password.length >= 8
    }

    fun firebaseAuth(): FirebaseUser? {
        var currentUser = auth.currentUser
        scope.launch {
            try {
                // this is fine for now but it needs to go to the signup page soon instead
                auth.createUserWithEmailAndPassword(email, password)
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