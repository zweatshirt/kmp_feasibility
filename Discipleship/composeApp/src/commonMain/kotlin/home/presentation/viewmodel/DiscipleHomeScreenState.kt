package home.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import home.domain.model.Tool

//
var discipleToolsList = mutableStateListOf<Tool>()
var toDoList = mutableStateListOf<Tool>()
var completedList = mutableStateListOf<Tool>()
