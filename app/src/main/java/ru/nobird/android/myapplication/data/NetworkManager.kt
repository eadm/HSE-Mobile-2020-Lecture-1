package ru.nobird.android.myapplication.data

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
import ru.nobird.android.myapplication.viewmodel.Cinema
import ru.nobird.android.myapplication.viewmodel.Movie
import java.util.Date
import java.util.concurrent.TimeUnit

interface MoviesService {
    @GET("movies")
    fun getMovies(): Single<List<Movie>>

    @POST("movies")
    fun createMovie(@Body movie: Movie): Single<Movie>

    @DELETE("movies/{id}")
    fun deleteMovie(@Path("id") id: Int): Completable

    @GET("cinemas")
    fun getCinema(@Query("id") cinema: Long): Single<List<Cinema>>

    @Headers(
        "User-Agent: Retrofit-Sample-App"
    )
    @GET("movies/{id}")
    fun getMovie(
        @Path("id") id: Int,

        @Query("name") name: String,
        @QueryMap queryMap: Map<String, String>,

        @HeaderMap headersMap: Map<String, String>
    ): Call<Movie>

    @PUT("movies/{id}")
    fun updateMovie(
        @Path("id") id: Int,
        @Body movie: Movie
    ): Call<Movie>
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
            .registerTypeAdapter(Date::class.java, UTCDateAdapter()) // можно добавлять адаптеры для кастомных типов
            .create()

        val converterFactory = GsonConverterFactory.create(gson)

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        service = retrofit.create()
    }

    fun getItems(): Single<List<Movie>> =
        service.getMovies()

    fun createItem(movie: Movie): Single<Movie> =
        service.createMovie(movie)

    fun deleteItem(movie: Movie): Completable =
        service.deleteMovie(movie.id)

    fun getCinema(id: Long): Single<List<Cinema>> =
        service.getCinema(id)
}