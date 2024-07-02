package home.presentation.viewmodel

import home.domain.model.Tool

//
data class DiscipleHomeScreenState(
    // populates by getTools() in DiscipleHomeScreen.kt
    var toolsList: List<Tool> = mutableListOf(),
    val toDoList: List<Tool> = emptyList(),
    val finishedTools: List<Tool> = emptyList()
)