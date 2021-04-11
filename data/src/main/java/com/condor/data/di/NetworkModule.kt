package com.condor.data.di

import com.condor.data.network.BASE_URL
import com.condor.data.network.SportApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val NETWORK_AVAILABLE_AGE = 10

val networkModule = module {
    val connectTimeout: Long = 10
    val readTimeout: Long = 10

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        okHttpClientBuilder.addInterceptor { chain ->
            val request = chain.request()
            val originalHttpUrl = request.url

            val url = originalHttpUrl.newBuilder()
                .build()

            chain.proceed(
                request.newBuilder()
                    .url(url)
                    .removeHeader("Pragma")
                    .addHeader("Content-type", "application/json")
                    .addHeader("Cache-Control", "public, max-age=$NETWORK_AVAILABLE_AGE")
                    .build()
            )
        }

        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    single { provideHttpClient() }

    single<SportApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(SportApi::class.java)

    }
}