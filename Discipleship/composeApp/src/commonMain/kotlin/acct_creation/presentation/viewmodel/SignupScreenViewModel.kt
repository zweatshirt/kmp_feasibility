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
import global_consts.Constants
import home.data.remote.ToolsApi
import home.data.repository.ToolsRepoImplementation
import home.presentation.viewmodel.DiscipleHomeViewModel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.mongodb.kbson.BsonObjectId
import profile.domain.model.User
import realm.data.remote.RealmApi
import realm.domain.model.UserEntity
import realm.domain.repository.RealmRepository


/* Author: Zachery Linscott
* This class handles user signup and authentication in that regard
* It should also instantiate the User object and populate our DB, their first name, last name,
* etc should be maintained, and the rest of the data such as their image will be null until
* they fill out our forms
* */

// TODO: CODE NEEDS TO BE WRITTEN TO POPULATE USER OBJECT :>
class SignupScreenViewModel(val realmRepository: RealmRepository): ViewModel() {
    // Database/Realm API
    val app = RealmApi.AtlasApp.app
    private val scope = viewModelScope
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")

    var signupValidation: SignupValidation by mutableStateOf(SignupValidation(
        emailValidationResult = ValidationResult(false, null),
        firstNameValidationResult = ValidationResult(false, null),
        lastNameValidationResult = ValidationResult(false, null),
        passwordValidationResult = ValidationResult(false, null),
        confirmPasswordValidationResult = ValidationResult(false, null),
        isValidated = false )
    )
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

    // User Creation
    fun createUserObject(): User {
        val user = User(
            uID = app.currentUser!!.id,
            firstName = firstName,
            lastName = lastName,
            email = email
        )
        return user
    }

    fun writeUserToDb() {
        scope.launch {
            Logger.i("Attempting to write user to Realm")
            // Create database object
            try {
                val userEntity = UserEntity().apply {
                    _id = app.currentUser!!.id
                    bio
                    email
                    firstName
                    lastName
                }
                realmRepository.writeUser(userEntity)
            }
            catch (e: Exception) {
                Logger.e("Failed to sync user")
            }
        }
    }

    // Validation Functions

    // Validate Email
    private val emailAddressRegex = Regex(
"[a-zA-Z0-9+._%\\-]{1,256}" +
            "@" +
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

    // Validate First Name
    private fun validateFirstName(): ValidationResult {
        val containsLetters = firstName.any { it.isLetter() }
        if(!containsLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid Name."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    // Validate Last Name
    private fun validateLastName(): ValidationResult {
        val containsLetters = lastName.any { it.isLetter() }
        if(!containsLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid Name."
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

    // Validate Repeated Password
    private fun validateConfirmPassword(): ValidationResult {
        if(password != confirmPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "Passwords do not match."
            )
        }
        return ValidationResult(
            successful = true
        )
    }

    // Call Validation on Submit
    fun validationOnSubmit(){
        signupValidation = SignupValidation(
            emailValidationResult = validateEmail(),
            firstNameValidationResult = validateFirstName(),
            lastNameValidationResult = validateLastName(),
            passwordValidationResult = validatePassword(),
            confirmPasswordValidationResult = validateConfirmPassword(),
            isValidated = false
        )
        if (signupValidation.emailValidationResult.successful &&
            signupValidation.firstNameValidationResult.successful &&
            signupValidation.lastNameValidationResult.successful &&
            signupValidation.passwordValidationResult.successful &&
            signupValidation.confirmPasswordValidationResult.successful
            ) {
            signupValidation.isValidated = true
        }
    }

    fun atlasAuth(): io.realm.kotlin.mongodb.User? {
        var currentUser: io.realm.kotlin.mongodb.User? = null
        runBlocking { // force execution of sign up auth
            try {
                app.currentUser?.logOut() // bad code, just in case a user is cached
                // this is fine for now but it needs to go to the signup page soon instead
                app.emailPasswordAuth.registerUser(email, password)
                Logger.i("Made it to login try")
                val emailPasswordCredentials = Credentials.emailPassword(email, password)
                currentUser = app.login(emailPasswordCredentials)
                Logger.i("Value of Atlas user ${app.currentUser}")
                if (currentUser != null) realmRepository.initRealm()
                else throw Exception("Failed to initialize Realm, app user is null")
            }
            catch(e: Exception) {
                // eventually want to populate the UI with a Snackbar indicating inability to login
                Logger.e("Exception found in atlasAuth, likely user doesn't exist")
            }
        }
        Logger.i("currentUser value: $currentUser")

        return currentUser
    }
}