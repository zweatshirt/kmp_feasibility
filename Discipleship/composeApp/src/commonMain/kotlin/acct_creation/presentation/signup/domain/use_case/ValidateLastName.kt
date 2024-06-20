package acct_creation.presentation.signup.domain.use_case

class ValidateLastName {

    fun execute(lastName: String): ValidationResult {
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
}