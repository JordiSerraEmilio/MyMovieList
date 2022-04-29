package com.example.mymovielist.models

import com.example.mymovielist.models.Genre.LlistaGenres
import com.example.mymovielist.models.Reviews.Reviews
import com.example.mymovielist.models.TopFilms.TopFilms
import com.example.mymovielist.models.Users.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @GET()
    suspend fun getGenresList(@Url url:String): Response<LlistaGenres>

    @GET()
    suspend fun getPopularFilms(@Url url:String): Response<TopFilms>

    @GET()
    suspend fun getListUsers(@Url url:String): Response<List<User>>

    @GET()
    suspend fun getListReviewsUser(@Url url:String): Response<List<Reviews>>

    //region REGISTER

    @POST("/users")
    fun postUser(@Body user: User): Call<User>

    object ServiceBuilder {
        private val client = OkHttpClient.Builder().build()

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://6o5zl5.deta.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }
    }

    //endregion
}