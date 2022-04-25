package com.example.mymovielist.models

import com.example.mymovielist.models.Genre.LlistaGenres
import com.example.mymovielist.models.TopFilms.TopFilms
import com.example.mymovielist.models.Users.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET()
    suspend fun getGenresList(@Url url:String): Response<LlistaGenres>

    @GET()
    suspend fun getPopularFilms(@Url url:String): Response<TopFilms>

    @GET()
    suspend fun getListUsers(@Url url:String): Response<List<User>>

}