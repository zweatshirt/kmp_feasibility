package navigation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch

class DataScreenModel(): ScreenModel {
    fun fetchData(){
        screenModelScope.launch {
            //fetch data code
        }
    }
}