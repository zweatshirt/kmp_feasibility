package calendar

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import calendar.ui.Calendar
class CalendarPage: Screen {
    @Composable
    override fun Content() {
        Calendar()
    }
}