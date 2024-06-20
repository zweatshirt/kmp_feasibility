package acct_creation.presentation.signup.domain.use_case

class ValidateEmail {

    private val emailAddressRegex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank."
            )
        }
        if(!email.matches(emailAddressRegex)) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid email."
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}