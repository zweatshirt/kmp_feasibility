package calendar.dateTime

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus


data class YearMonth(val year: Int, val month: Month) {

    fun getDayOfMonthStartingFromMonday(): List<LocalDate> {
        val firstDayOfMonth = LocalDate(year, month, 1)
        var firstSundayOfMonth = firstDayOfMonth
        while (firstSundayOfMonth.dayOfWeek != DayOfWeek.SUNDAY) {
            firstSundayOfMonth = firstSundayOfMonth.plus(1, DateTimeUnit.DAY)
        }
        val firstDayOfNextMonth = firstDayOfMonth.plus(1, DateTimeUnit.MONTH)

        if (firstDayOfMonth == firstSundayOfMonth) {
            return generateSequence(firstSundayOfMonth) { it.plus(1, DateTimeUnit.DAY) }
                .takeWhile { it != firstDayOfNextMonth}
                .toList()
        } else {
            return generateSequence(firstSundayOfMonth.minus(7, DateTimeUnit.DAY)) { it.plus(1, DateTimeUnit.DAY) }
                .takeWhile { it != firstDayOfNextMonth}
                .toList()
        }

    }

    fun plusMonths(months: Int): YearMonth {
        val newMonth = month.number + months
        val newYear = year + (newMonth - 1) / 12
        val newMonthNumber = (newMonth - 1) % 12 + 1
        return YearMonth(newYear, newMonthNumber.toMonth())
    }
    fun minusMonths(months: Int): YearMonth {
        val totalMonths = year * 12 + (month.number - 1) - months
        val newYear = totalMonths / 12
        val newMonth = (totalMonths % 12) + 1 // Adding 1 because months are 1-based
        return YearMonth(newYear, newMonth.toMonth())
    }

    private fun Int.toMonth(): Month {
        return Month.entries[this - 1]
    }

}