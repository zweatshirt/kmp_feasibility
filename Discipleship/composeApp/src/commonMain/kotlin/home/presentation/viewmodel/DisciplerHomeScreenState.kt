package home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import home.domain.model.Tool

var disciplerToolsList: List<Tool>  = mutableStateListOf()
var recommendedList: List<Tool> = mutableStateListOf()
    private set
var disciplerList: List<Tool> = mutableStateListOf()
    private set
