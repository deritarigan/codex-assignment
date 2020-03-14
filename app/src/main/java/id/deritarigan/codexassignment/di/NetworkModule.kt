package id.deritarigan.codexassignment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import id.deritarigan.codexassignment.Config
import id.deritarigan.codexassignment.model.api.IApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val TIME_OUT = 10L

    val module = module {
        single { createOkHttpClient() }
        single { createApiService(get()) }
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    private fun createApiService(okHttpClient: OkHttpClient): IApi {
        val gsonBuilder = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()

        return retrofit.create(IApi::class.java)
    }
}