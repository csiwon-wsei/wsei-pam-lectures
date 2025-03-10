package pl.wsei.pam.lectures.lecture6.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {
    companion object {
        val BASE_URL = "https://api.nbp.pl/api/exchangerates/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .add(LocalDateAdapter())
                            .build()
                    )
                )
                .build()
        }
    }
}
