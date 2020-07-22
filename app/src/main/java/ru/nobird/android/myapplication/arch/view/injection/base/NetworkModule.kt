package ru.nobird.android.myapplication.arch.view.injection.base

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.nobird.android.myapplication.arch.remote.base.mapper.UTCDateAdapter
import java.util.Date
import java.util.concurrent.TimeUnit

object NetworkModule {
    private const val BASE_URL = "http://10.0.2.2:3000"
    private const val TIMEOUT = 30000L

    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

    fun provideGson(): Gson =
        GsonBuilder()
            .registerTypeAdapter(Date::class.java,
                UTCDateAdapter()
            ) // можно добавлять адаптеры для кастомных типов
            .create()

    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
}