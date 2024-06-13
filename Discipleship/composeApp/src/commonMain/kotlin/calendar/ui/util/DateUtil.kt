package calendar.ui.util

import calendar.dateTime.YearMonth
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.plus


object DateUtil {

    val daysOfWeek: Array<String>
        get() {
            val daysOfWeek = Array(7) { "" }

            for (dayOfWeek in DayOfWeek.entries) {
                daysOfWeek[dayOfWeek.isoDayNumber - 1] = dayOfWeek.toString()
            }

            return daysOfWeek
        }
}

fun YearMonth.getDayOfMonthStartingFromMonday(): List<LocalDate> {
    val firstDayOfMonth = LocalDate(year, month, 1)
    var firstMondayOfMonth = firstDayOfMonth
    while (firstMondayOfMonth.dayOfWeek != DayOfWeek.MONDAY) {
        firstMondayOfMonth = firstMondayOfMonth.plus(1, DateTimeUnit.DAY)
    }
    val firstDayOfNextMonth = firstDayOfMonth.plus(1, DateTimeUnit.MONTH)

    return generateSequence(firstDayOfMonth) { it.plus(1, DateTimeUnit.DAY) }
        .takeWhile { it != firstDayOfNextMonth }
        .toList()
}

fun YearMonth.getDisplayName(): String {
    return "$month $year"
}