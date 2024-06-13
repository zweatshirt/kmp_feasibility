package calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import io.wojciechosak.calendar.config.rememberCalendarState
import io.wojciechosak.calendar.utils.today
import io.wojciechosak.calendar.view.CalendarView
import io.wojciechosak.calendar.view.HorizontalCalendarView
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun calendarPage() {
    val startDate: LocalDate = LocalDate.today()
    HorizontalCalendarView(startDate = startDate) { monthOffset ->
        CalendarView(
            config = rememberCalendarState(
                startDate = startDate,
                monthOffset = monthOffset,
            ),
        )
    }
}