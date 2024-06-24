package acct_creation.presentation.viewmodel

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
