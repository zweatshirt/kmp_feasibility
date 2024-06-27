package home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import home.domain.model.Tool
import home.domain.repository.ToolsRepository
import kotlinx.coroutines.flow.MutableStateFlow
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

    fun getTools() {
        _scope.launch {
            var toolObjects: List<Tool> = mutableListOf()
            // returns Either<NetworkError, List<Tool>>
            toolsRepository.getTools()
                .onRight { tools ->
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

            if (toolObjects.isNotEmpty()) {
                toolsRepository.writeToolsToDb(toolObjects)
            }
            else {
                Logger.e("toolObjects failed to populate")
            }
        }
    }
}