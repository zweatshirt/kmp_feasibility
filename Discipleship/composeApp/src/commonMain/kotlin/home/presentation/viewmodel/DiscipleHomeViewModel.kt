package home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
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
    val scope = viewModelScope
    fun getTools() {
        scope.launch {
            // returns Either<NetworkError, List<Tool>>
            toolsRepository.getTools()
                .onRight { tools ->
                    // update state with the tools
                    Logger.i(tools.toString())
                    Logger.i("Successful Tools API request in DiscipleHomeViewModel")
                } // successful request, returns list of tools
                .onLeft {
                    Logger.e("Failed request for Tools API in DiscipleHomeViewModel")
                } // unsuccessful request, throws NetworkError
        }
    }
}