package pl.wsei.pam.lectures.lecture6.network

import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.time.LocalDate


interface NBPApiService {
    @Headers("Accept: application/json")
    @GET("tables/{table}")
    suspend fun getTable(@Path("table") table: String): List<RateTable>
}

@JsonClass(generateAdapter = true)
data class Rate(
    val currency: String,
    val code: String,
    val bid: Double,
    val ask: Double
)
@JsonClass(generateAdapter = true)
data class RateTable(
    val table: String,
    val no: String,
    val tradingDate: LocalDate,
    val effectiveDate: LocalDate,
    val rates: MutableList<Rate>
)