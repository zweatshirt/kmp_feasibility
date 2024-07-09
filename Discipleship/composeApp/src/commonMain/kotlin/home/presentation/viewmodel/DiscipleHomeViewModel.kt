package home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import home.domain.model.Tool
import home.domain.repository.ToolsRepository
import kotlinx.coroutines.launch

/* Author: Zach and Josh */
// the constructor is supposed to be injected
// with the @Inject annotation
// but I currently don't know how to do that
// easily in Kotlin Multiplatform
class DiscipleHomeViewModel constructor(
    private val toolsRepository: ToolsRepository
): ViewModel() {
//    private val _state = MutableStateFlow()
    private val _scope = viewModelScope
    private val isPopulated = mutableStateOf(false)

//    var discipleHomeScreenState by mutableStateOf(DiscipleHomeScreenState())
//        private set

    fun getTools() {
        if (!isPopulated.value) {
            var toolObjects: List<Tool> = mutableListOf()
            _scope.launch {
                // returns Either<NetworkError, List<Tool>>
                toolsRepository.getTools()
                    .onRight { tools ->
                        // this is for testing
                        toolsRepository.readToolsFromDb()
                        // update state with the tools
                        Logger.i("Successful Tools API request in DiscipleHomeViewModel")
                        toolObjects = tools
                        toolObjects.forEach {
                            Logger.i("$it")
                        }
                    } // successful request, returns list of tools
                    .onLeft {
                        Logger.e("Failed request for Tools API in DiscipleHomeViewModel")
                    } // unsuccessful request, throws NetworkError
                //discipleHomeScreenState.toolsList = toolObjects
                discipleToolsList.addAll(toolObjects.toMutableStateList())
                if (!discipleToolsList.isEmpty()) isPopulated.value = true
                Logger.i("The size of the tool list is: ${discipleToolsList.size}")
            }
        }
    }

    fun addToTodoList() {
        TODO()
    }
}