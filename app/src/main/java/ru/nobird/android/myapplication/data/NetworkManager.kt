package ru.nobird.android.myapplication.data

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.nobird.android.myapplication.viewmodel.Item
import java.util.concurrent.TimeUnit

interface MoviesService {
    @GET("movies")
    fun getMovies(): Call<List<Item>>

    @POST("movies")
    fun createMovie(@Body item: Item): Call<Item>

    @DELETE("movies/{id}")
    fun deleteMovie(@Path("id") id: Int): Call<Void>


    @Headers(
        "User-Agent: Retrofit-Sample-App"
    )
    @GET("movies/{id}")
    fun getMovie(
        @Path("id") id: Int,

        @Query("name") name: String,
        @QueryMap queryMap: Map<String, String>,

        @HeaderMap headersMap: Map<String, String>
    ): Call<Item>

    @PUT("movies/{id}")
    fun updateMovie(
        @Path("id") id: Int,
        @Body item: Item
    ): Call<Item>
}

object NetworkManager {
    private const val BASE_URL = "http://10.0.2.2:3000"

    private const val TIMEOUT = 30000L

    private val service: MoviesService

    init {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor { it.proceed(it.request()) }
            .addNetworkInterceptor {
                Log.d(javaClass.canonicalName, it.request().url().toString())
                it.proceed(it.request())
            }
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .create()

        val converterFactory = GsonConverterFactory.create(gson)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

        service = retrofit.create()
    }

    fun getItems(): Call<List<Item>> =
        service.getMovies()

    fun createItem(item: Item): Call<Item> =
        service.createMovie(item)

    fun deleteItem(item: Item): Call<Void> =
        service.deleteMovie(item.id)
}