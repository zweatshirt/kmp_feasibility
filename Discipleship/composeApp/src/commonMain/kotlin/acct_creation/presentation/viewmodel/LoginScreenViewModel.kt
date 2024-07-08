package acct_creation.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import realm.data.remote.RealmApi
import realm.domain.model.UserEntity
import realm.domain.repository.RealmRepository

//import realm.RealmRepository

/* Author: Zachery Linscott
* This class handles user authentication
* We also want the class to populate the User object with backend user data
* We may use Firebase for this.
* We should try to typecast the User to a Disciple or Discipler object
* */
class LoginScreenViewModel(val realmRepository: RealmRepository): ViewModel() {
    private var app = RealmApi.AtlasApp.app
    private val scope = viewModelScope
    var userSignedIn = false


    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var emailResult by mutableStateOf(ValidationResult(false, null))
    var passwordResult by mutableStateOf(ValidationResult(false, null))


    fun updateEmail(input: String) {
        email = input
    }
    fun updatePassword(input: String) {
        password = input
    }

    // Validate Email
    private val emailAddressRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    private fun validateEmail(): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email can't be blank."
            )
        }
        if(!email.matches(emailAddressRegex)) {
            return ValidationResult(
                successful = false,
                errorMessage = "Not a valid email."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    // Validate Password
    private fun validatePassword(): ValidationResult {
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least 8 characters."
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain one letter and number."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    fun loginIsValid(): Boolean {
        emailResult = validateEmail()
        passwordResult = validatePassword()


        if (emailResult.successful &&
            passwordResult.successful) {
            return true
        }
        return false
    }

    fun atlasAuth(): io.realm.kotlin.mongodb.User? {
        var currentUser: io.realm.kotlin.mongodb.User? = null
        runBlocking { // force this to execute before anything else happens
            try {
                app.currentUser?.logOut()
                // this is fine for now but it needs to go to the signup page soon instead
                val emailPasswordCredentials = Credentials.emailPassword(email, password)
                currentUser = app.login(emailPasswordCredentials)
                Logger.i("Made it to login try")
                Logger.i("Value of Atlas user $currentUser")
                if (currentUser != null) {
                    realmRepository.initRealm()
                }
                else {
                    throw NullPointerException("Login failed, current user is null")
                }
            }
            catch(e: NullPointerException) {
                // eventually want to populate the UI with a Snackbar indicating inability to login
                Logger.e("Exception found in firebaseAuth, likely user doesn't exist")
                if (e.message != null) Logger.e(e.message!!)
            }
            catch(e: Exception) {
                Logger.e("Exception in atlasAuth()")
            }
        }
        Logger.i("currentUser value: $currentUser")
        return currentUser
    }

    fun fetchUserData(currentUser: io.realm.kotlin.mongodb.User?): UserEntity? {
        var userEntity: UserEntity? = null
        scope.launch {
            try {
                if (currentUser == null) {
                    throw NullPointerException(
                        "Fetching user data failed in fetchUserData()," +
                                " Atlas User object is null"
                    )
                }
                if (RealmApi.RealmInstance.realm == null)
                    throw NullPointerException("Realm is not open, failed to" +
                            "fetch user data in fetchUserData()")
                userEntity = realmRepository.readUser(currentUser.id)
                // Build User object from User Entity:
            }
            catch (nullP: NullPointerException) {
                if (nullP.message != null)
                    Logger.e(nullP.message!!)
            }
            catch (e: Exception) {
                Logger.e("Exception in fetchUserData()")
            }
        }
        return userEntity
    }
}