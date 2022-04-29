package com.example.mymovielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityEscollirTemesBinding
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Genre.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EscollirTemes : AppCompatActivity() {
    private lateinit var binding : ActivityEscollirTemesBinding
    private lateinit var adapter: GenreAdapter

    private var generos = mutableListOf<Genres>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEscollirTemesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initRecyclerView()
        buscarGeneros()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/genre/movie/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun buscarGeneros() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getGenresList("list?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=es-ES")
            val nombre = call.body()?.genres

            runOnUiThread {
                if (nombre != null) {
                    initGenres(nombre)
                }
            }
        }
    }

    private fun initGenres(genres: List<Genres>?){
        for (genre in genres!!){
            generos.add(genre)
        }
        adapter = GenreAdapter(generos)
        binding.rvChooseGenres.layoutManager = GridLayoutManager(this, 2)
        binding.rvChooseGenres.adapter = adapter
    }
}