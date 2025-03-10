package pl.wsei.pam.lectures.lecture6.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter {
    private val pattern = "yyyy-MM-dd"
    @ToJson
    fun toJson(date: LocalDate): String{
        return date.format(DateTimeFormatter.ofPattern(pattern))
    }

    @FromJson
    fun fromJson(dateStr: String): LocalDate {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern))
    }

}