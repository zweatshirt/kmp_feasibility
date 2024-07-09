package home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import home.domain.model.Tool

var disciplerToolsList= mutableStateListOf<Tool>()
var recommendedList = mutableStateListOf<Tool>()
var disciplerList = mutableStateListOf<Tool>()

