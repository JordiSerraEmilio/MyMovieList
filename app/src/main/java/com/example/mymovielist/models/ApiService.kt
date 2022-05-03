package com.example.mymovielist.models

import com.example.mymovielist.models.Genre.LlistaGenres
import com.example.mymovielist.models.Review.Reviews
import com.example.mymovielist.models.TopFilms.TopFilms
import com.example.mymovielist.models.Users.User
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @GET()
    suspend fun getGenresList(@Url url:String): Response<LlistaGenres>

    @GET()
    suspend fun getPopularFilms(@Url url:String): Response<TopFilms>

    @GET()
    suspend fun getListUsers(@Url url:String): Response<List<User>>

    @GET()
    suspend fun getUser(@Url url:String): Response<User>

    @GET()
    suspend fun getListReviewsUser(@Url url:String): Response<List<Reviews>>

    //region REGISTER
//    @GET("/users/{email}")
//    fun getUser(@Url email: String): Call<User>
//    object ServiceBuilderGet {
//        private val client = OkHttpClient.Builder().build()
//
//        private val retrofit = Retrofit.Builder()
//            .baseUrl("https://6o5zl5.deta.dev/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        fun<T> buildService(service: Class<T>): T{
//            return retrofit.create(service)
//        }
//    }

    @GET()
    suspend fun getUser(@Url url:String): Response<User>


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

    @PUT("/{email}")
    fun putUser(@Url email: String, @Body user: User): Call<User>

    object ServiceBuilderPut {
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