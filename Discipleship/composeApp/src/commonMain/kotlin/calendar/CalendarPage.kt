package calendar

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import calendar.presentation.Calendar
import viewmodel.ScreenData

data class CalendarPage(val screenData: ScreenData): Screen {
    override val key = "Calendar"
    @Composable
    override fun Content() {
        Calendar(screenData)
    }
}
