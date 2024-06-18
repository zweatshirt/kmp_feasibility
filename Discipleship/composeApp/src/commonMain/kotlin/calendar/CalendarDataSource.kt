package calendar

import calendar.dateTime.YearMonth
import calendar.dateTime.now
import calendar.domain.model.CalendarUiState
import kotlinx.datetime.LocalDate


/**
 * Created by meyta.taliti on 20/05/23.
 */
class CalendarDataSource {

    fun getDates(yearMonth: YearMonth): List<CalendarUiState.Date> {
        return yearMonth.getDayOfMonthStartingFromMonday()
            .map { date:LocalDate ->
                CalendarUiState.Date(
                    dayOfMonth = if (date.month == yearMonth.month) {
                        "${date.dayOfMonth}"
                    } else {
                        "" // Fill with empty string for days outside the current month
                    },
                    isSelected = date.equals(LocalDate.now()) && date.month == yearMonth.month
                )
            }
    }
}