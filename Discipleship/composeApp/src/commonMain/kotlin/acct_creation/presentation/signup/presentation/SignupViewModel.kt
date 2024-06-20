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

class SignupViewModel(
    private val validateFirstName: ValidateFirstName = ValidateFirstName(),
    private val validateLastName: ValidateLastName = ValidateLastName(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
): ViewModel() {

    var state by mutableStateOf(SignupFormState())


}