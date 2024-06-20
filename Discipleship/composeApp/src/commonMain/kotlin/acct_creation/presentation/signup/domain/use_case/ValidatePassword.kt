package acct_creation.presentation.signup.domain.use_case

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if(password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters."
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "The password must contain one letter and number."
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}