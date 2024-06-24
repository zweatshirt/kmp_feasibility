package acct_creation.presentation.viewmodel

data class SignupValidation(
    val emailValidationResult: ValidationResult,
    val firstNameValidationResult: ValidationResult,
    val lastNameValidationResult: ValidationResult,
    val passwordValidationResult: ValidationResult,
    val confirmPasswordValidationResult: ValidationResult,
    var isValidated: Boolean
)
