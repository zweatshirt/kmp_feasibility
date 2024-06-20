package acct_creation.presentation.signup.domain.use_case

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords do not match."
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}