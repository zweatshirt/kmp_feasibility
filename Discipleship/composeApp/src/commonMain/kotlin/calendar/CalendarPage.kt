package calendar

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import calendar.ui.Calendar
import navigation.ScreenData

data class CalendarPage(val screenData: ScreenData): Screen {
    @Composable
    override fun Content() {
        Calendar(screenData)
    }
}