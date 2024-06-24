package acct_creation.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DisciplerFormViewModel: ViewModel() {
    private var haveDiscipled by mutableStateOf("")

    var yearsOfExperience by mutableStateOf("")

    private var bibleKnowledge by mutableStateOf(0)

    private var evangalismExperience by mutableStateOf("")

    var haveDiscipledResult by mutableStateOf( ValidationResult(false, null))
    var yearsOfExperienceResult by mutableStateOf( ValidationResult(false, null))
    var evanglismExperienceResult by mutableStateOf( ValidationResult(false, null))

    fun updateHaveDisicpled(input: String) {
        haveDiscipled = input
    }

    fun updateYearsOfExperience(input: String) {
        yearsOfExperience = input
    }

    fun updateBibleKnowledge(input: Int) {
        bibleKnowledge = input
    }

    fun updateEvangalismExperience(input: String) {
        evangalismExperience = input
    }

    private fun validateHaveDiscipled(): ValidationResult {
        if (haveDiscipled.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Pick Yes or No."
            )
        }

        return ValidationResult(
            successful = true
        )
    }

    private fun validateYearsOfExperience(): ValidationResult {
        if (yearsOfExperience.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Input your experience."
            )
        }
        val containsLetters = yearsOfExperience.any { it.isLetter() }
        if(containsLetters) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid Experience."
            )
        }

        return ValidationResult(
            successful = true
        )
    }

    private fun validateEvangalismExperience(): ValidationResult {
        if (evangalismExperience.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Pick Experience Level"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

    fun disciplerFormIsValid(): Boolean {
        haveDiscipledResult = validateHaveDiscipled()
        yearsOfExperienceResult = validateYearsOfExperience()
        evanglismExperienceResult = validateEvangalismExperience()

        if (haveDiscipledResult.successful &&
            yearsOfExperienceResult.successful &&
            evanglismExperienceResult.successful) {
            return true
        }
        return false
    }

}