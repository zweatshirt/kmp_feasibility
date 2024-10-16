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
    var vmEmail by mutableStateOf("")
        private set
    var vmPassword by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var vmFirstName by mutableStateOf("")
        private set
    var vmLastName by mutableStateOf("")

    var signupValidation: SignupValidation by mutableStateOf(SignupValidation(
        emailValidationResult = ValidationResult(false, null),
        firstNameValidationResult = ValidationResult(false, null),
        lastNameValidationResult = ValidationResult(false, null),
        passwordValidationResult = ValidationResult(false, null),
        confirmPasswordValidationResult = ValidationResult(false, null),
        isValidated = false )
    )
    fun updateEmail(input: String) {
        vmEmail = input
    }
    fun updatePassword(input: String) {
        vmPassword = input
    }
    fun updateConfirmPassword(input: String) {
        confirmPassword = input
    }
    fun updateFirstName(input: String) {
        vmFirstName = input
    }
    fun updateLastName(input: String) {
        vmLastName = input
    }

    // User Creation
    fun createUserObject(): User {
        val user = User(
            uID = app.currentUser!!.id,
            firstName = vmFirstName,
            lastName = vmLastName,
            email = vmEmail
        )
        return user
    }

    fun writeUserToDb(): UserEntity? {
        var userEntity: UserEntity? = null
        scope.launch {
            Logger.i("Attempting to write user to Realm")
            // Create database object
            try {
                userEntity = UserEntity().apply {
                    _id = app.currentUser!!.id
                    bio = ""
                    email = vmEmail
                    firstName = vmFirstName
                    lastName = vmLastName
                    isDisciple = null
                }
                realmRepository.writeUser(userEntity!!)
            }
            catch (e: Exception) {
                Logger.e("Failed to sync user")
            }
        }
        return userEntity
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
        if(vmEmail.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email can't be blank."
            )
        }
        if(!vmEmail.matches(emailAddressRegex)) {
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
        val containsLetters = vmFirstName.any { it.isLetter() }
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
        val containsLetters = vmLastName.any { it.isLetter() }
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
        if(vmPassword.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password must contain at least 8 characters."
            )
        }
        val containsLettersAndDigits = vmPassword.any { it.isDigit() } && vmPassword.any { it.isLetter() }
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
        if(vmPassword != confirmPassword) {
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
        runBlocking { // force execution of sign up auth with runBlocking if needed
            try {
                app.currentUser?.logOut() // bad code, just in case a user is cached
                // this is fine for now but it needs to go to the signup page soon instead
                app.emailPasswordAuth.registerUser(vmEmail, vmPassword)
                Logger.i("Made it to login try")
                val emailPasswordCredentials = Credentials.emailPassword(vmEmail, vmPassword)
                currentUser = app.login(emailPasswordCredentials)
                Logger.i("Value of Atlas user ${app.currentUser}")
            }
            catch(e: Exception) {
                // eventually want to populate the UI with a Snackbar indicating inability to login
                Logger.e("Exception found in atlasAuth, likely user doesn't exist")
                return@runBlocking
            }
        }
        Logger.i("currentUser value: $currentUser")

        return currentUser
    }

    fun initRealm() {
        scope.launch {
            realmRepository.initRealm()
        }
    }
}