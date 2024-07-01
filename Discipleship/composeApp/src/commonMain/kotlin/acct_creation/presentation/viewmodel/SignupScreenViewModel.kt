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
import profile.domain.model.User


/* Author: Zachery Linscott
* This class handles user signup and authentication in that regard
* It should also instantiate the User object and populate our DB, their first name, last name,
* etc should be maintained, and the rest of the data such as their image will be null until
* they fill out our forms
* */

// TODO: CODE NEEDS TO BE WRITTEN TO POPULATE USER OBJECT :>
class SignupScreenViewModel: ViewModel() {
    private var auth = Firebase.auth
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
    fun userCreation(): User {
        val user = User(
            uID = auth.currentUser!!.uid,
            firstName = firstName,
            lastName = lastName,
            email = email
        )
        return user
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

    fun firebaseAuth(): FirebaseUser? {
        val currentUser = auth.currentUser
        scope.launch {
            try {
                // this is fine for now but it needs to go to the signup page soon instead
                auth.createUserWithEmailAndPassword(email, password)
                Logger.i("Made it to login try")
                Logger.i("Value of firebase user ${auth.currentUser}")
            }
            catch(e: Exception) {
                // eventually want to populate the UI with a Snackbar indicating inability to login
                Logger.e("Exception found in firebaseAuth, likely user doesn't exist")
            }
        }
        Logger.i("currentUser value: $currentUser")
        return currentUser
    }
}