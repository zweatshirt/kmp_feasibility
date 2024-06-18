package calendar.domain.model

import calendar.dateTime.YearMonth
import io.wojciechosak.calendar.utils.today
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
            yearMonth = YearMonth(year = LocalDate.today().year, month = LocalDate.today().month),
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