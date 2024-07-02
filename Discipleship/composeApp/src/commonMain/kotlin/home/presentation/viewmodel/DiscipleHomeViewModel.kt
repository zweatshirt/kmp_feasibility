package home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import dev.gitlive.firebase.Firebase
import home.domain.model.Tool
import home.domain.repository.ToolsRepository
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
                    // this is for testing
                    toolsRepository.readToolsFromDb()
                    // update state with the tools
                    Logger.i("Successful Tools API request in DiscipleHomeViewModel")
                    toolObjects = tools
                } // successful request, returns list of tools
                .onLeft {
                    Logger.e("Failed request for Tools API in DiscipleHomeViewModel")
                } // unsuccessful request, throws NetworkError

        }
    }
}