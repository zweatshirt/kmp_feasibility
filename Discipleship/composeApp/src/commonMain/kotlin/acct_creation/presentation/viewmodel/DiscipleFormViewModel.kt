package acct_creation.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DiscipleFormViewModel {
    private var haveBeenDiscipled by mutableStateOf("")

    private var bibleKnowledge by mutableStateOf(0)

    private var evangalismExperience by mutableStateOf("")

    var haveBeenDiscipledResult by mutableStateOf( ValidationResult(false, null))
    var evanglismExperienceResult by mutableStateOf( ValidationResult(false, null))

    fun updateHaveDisicpled(input: String) {
        haveBeenDiscipled = input
    }

    fun updateBibleKnowledge(input: Int) {
        bibleKnowledge = input
    }

    fun updateEvangalismExperience(input: String) {
        evangalismExperience = input
    }

    private fun validateHaveDiscipled(): ValidationResult {
        if (haveBeenDiscipled.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Pick Yes or No."
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

    fun discipleFormIsValid(): Boolean {
        haveBeenDiscipledResult = validateHaveDiscipled()
        evanglismExperienceResult = validateEvangalismExperience()

        if (haveBeenDiscipledResult.successful &&
            evanglismExperienceResult.successful) {
            return true
        }
        return false
    }
}