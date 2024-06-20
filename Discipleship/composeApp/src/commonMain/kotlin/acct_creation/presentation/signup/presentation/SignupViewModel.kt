package acct_creation.presentation.signup.presentation

import acct_creation.presentation.signup.domain.use_case.ValidateEmail
import acct_creation.presentation.signup.domain.use_case.ValidateFirstName
import acct_creation.presentation.signup.domain.use_case.ValidateLastName
import acct_creation.presentation.signup.domain.use_case.ValidatePassword
import acct_creation.presentation.signup.domain.use_case.ValidateRepeatedPassword
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.valid
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
): ViewModel() {

    var state by mutableStateOf(SignupFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignupFormEvent) {
        when(event) {
            is SignupFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }
            is SignupFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }
            is SignupFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignupFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is SignupFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }
            is SignupFormEvent.CreateAccount -> {
                createAccount()
            }
        }
    }

    private fun createAccount() {
        val firstNameResult = validateFirstName.execute(state.firstName)
        val lastNameResult = validateLastName.execute(state.lastName)
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult = validateRepeatedPassword.execute(
            state.password, state.repeatedPassword
        )

        val hasError = listOf(
            firstNameResult,
            lastNameResult,
            emailResult,
            passwordResult,
            repeatedPasswordResult
        ).any { !it.successful }

        if(hasError) {
            state = state.copy(
                firstNameError = firstNameResult.errorMessage,
                lastNameError = lastNameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}