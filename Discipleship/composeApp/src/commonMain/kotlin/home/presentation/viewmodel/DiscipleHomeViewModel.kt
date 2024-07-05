package home.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import home.domain.model.Tool
import home.domain.repository.ToolsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/* Author: Zach */
// the constructor is supposed to be injected
// with the @Inject annotation
// but I currently don't know how to do that
// easily in Kotlin Multiplatform
class DiscipleHomeViewModel constructor(
    private val toolsRepository: ToolsRepository
): ViewModel() {
//    private val _state = MutableStateFlow()
    private val _scope = viewModelScope

    var discipleHomeScreenState by mutableStateOf(DiscipleHomeScreenState())
        private set

    var toolList: List<Tool> by mutableStateOf(mutableListOf<Tool>())
        private set


    fun getTools() {
        var toolObjects: List<Tool> = mutableListOf()
        _scope.launch {

            // returns Either<NetworkError, List<Tool>>
            toolsRepository.getTools()
                .onRight { tools ->
                    // this is for testing
                    toolsRepository.readToolsFromDb()
                    // update state with the tools
                    tools.forEach {
                        Logger.i(it.name)
                    }
                    Logger.i("Successful Tools API request in DiscipleHomeViewModel")
                    toolObjects = tools
                } // successful request, returns list of tools
                .onLeft {
                    Logger.e("Failed request for Tools API in DiscipleHomeViewModel")
                } // unsuccessful request, throws NetworkError
            //discipleHomeScreenState.toolsList = toolObjects
            toolList = toolObjects
            Logger.i("The size of the tool list is: ${discipleHomeScreenState.toolsList.size}")
        }
    }
}