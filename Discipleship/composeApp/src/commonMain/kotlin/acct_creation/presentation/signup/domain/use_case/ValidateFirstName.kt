package acct_creation.presentation.signup.domain.use_case

class ValidateFirstName {

    fun execute(firstName: String): ValidationResult {
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
}