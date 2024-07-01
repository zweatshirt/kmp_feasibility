package home.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import home.domain.model.Tool

//
data class DiscipleHomeScreenState (
    // populates by getTools() in DiscipleHomeScreen.kt
    val toolsList: List<Tool> = emptyList(),
    val toDoList: List<Tool> = emptyList(),
    val finishedTools: List<Tool> = emptyList(),
)