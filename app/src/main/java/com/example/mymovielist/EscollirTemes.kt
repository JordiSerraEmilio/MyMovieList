package com.example.mymovielist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityEscollirTemesBinding
import com.example.mymovielist.login.RestApiService
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Genre.GenreAdapter
import com.example.mymovielist.models.Genre.Genres
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EscollirTemes : AppCompatActivity() {
    private lateinit var binding: ActivityEscollirTemesBinding
    private lateinit var adapter: GenreAdapter

    private var generos = mutableListOf<Genres>()
    private var user_genres = ArrayList<Genres>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEscollirTemesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initRecyclerView()
        buscarGeneros()

        //
        binding.bttnAddGenres.setOnClickListener {
            AddGenresToUser()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/genre/movie/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun buscarGeneros() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getGenresList("list?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=us-US")
            val genre = call.body()?.genres

            runOnUiThread {
                if (genre != null) {
                    initGenres(genre)
                }
            }
        }
    }

    private fun initGenres(genres: List<Genres>?) {
        for (genre in genres!!) {
            generos.add(genre)
        }
        adapter = GenreAdapter(generos, user_genres)
        binding.rvChooseGenres.layoutManager = GridLayoutManager(this, 3)
        binding.rvChooseGenres.adapter = adapter

    }

    //

    private fun AddGenresToUser() {
        //Coge la lista del adapter y hace un PUT añadiendole la lista
//        user_genres = intent.extras?.getParcelable<Genres>("data")
//        Toast.makeText(applicationContext, user_genres.toString(), Toast.LENGTH_LONG).show()



        // TESTING
        val shared: SharedPreferences = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val test = shared.getString("email", "")
        Toast.makeText(applicationContext, test.toString(), Toast.LENGTH_SHORT).show()
    }
    //
}