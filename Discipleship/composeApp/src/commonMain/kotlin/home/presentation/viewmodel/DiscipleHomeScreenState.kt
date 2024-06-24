package home.presentation.viewmodel

import home.domain.model.Tool

//
data class DiscipleHomeScreenState (
    // populates by getTools() in DiscipleHomeScreen.kt
    val toolsList: List<Tool> = emptyList(),
    val toDoList: List<Tool> = emptyList(),
    val finishedTools: List<Tool> = emptyList()
)