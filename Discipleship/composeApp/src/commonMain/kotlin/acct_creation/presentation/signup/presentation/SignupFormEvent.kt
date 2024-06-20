package acct_creation.presentation.signup.presentation

import kotlinx.coroutines.selects.RegistrationFunction

sealed class SignupFormEvent {
    data class FirstNameChanged(val firstName: String): SignupFormEvent()
    data class LastNameChanged(val lastName: String): SignupFormEvent()
    data class EmailChanged(val email: String): SignupFormEvent()
    data class PasswordChanged(val password: String): SignupFormEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String): SignupFormEvent()

    data object CreateAccount: SignupFormEvent()
}