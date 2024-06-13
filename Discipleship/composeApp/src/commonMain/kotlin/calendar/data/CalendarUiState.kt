package calendar.data

import calendar.dateTime.YearMonth
import calendar.dateTime.now
import kotlinx.datetime.LocalDate


/**
 * Created by meyta.taliti on 20/05/23.
 */
data class CalendarUiState(
    val yearMonth: YearMonth,
    val dates: List<Date>
) {
    companion object {
        val Init = CalendarUiState(
            yearMonth = YearMonth(LocalDate.now().dayOfMonth, LocalDate.now().month),
            dates = emptyList()
        )
    }
    data class Date(
        val dayOfMonth: String,
        val isSelected: Boolean
    ) {
        companion object {
            val Empty = Date("", false)
        }
    }
}