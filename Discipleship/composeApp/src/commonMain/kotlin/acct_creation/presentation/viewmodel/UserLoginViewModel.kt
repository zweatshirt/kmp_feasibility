package acct_creation.presentation.viewmodel

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.launch

class UserLoginViewModel(): ViewModel() {
    var auth = Firebase.auth
    val scope = viewModelScope
    var firebaseUser: FirebaseUser? = null
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
    fun firebaseAuth(): Boolean {
        var loginSuccess: Boolean = false
        scope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
            }
            catch(e: Exception) {
                // this is fine for now but it needs to go to the signup page soon instead
                auth.signInWithEmailAndPassword(email, password)
            }

        }
        return loginSuccess

    }
}