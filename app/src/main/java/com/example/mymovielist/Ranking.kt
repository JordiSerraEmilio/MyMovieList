package com.example.mymovielist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityRankingBinding
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.TopFilms.ResultsTop
import com.example.mymovielist.models.TopFilms.TopAdapter
import com.example.mymovielist.models.TopFilms.TopFilms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ranking : AppCompatActivity() {

    private lateinit var binding : ActivityRankingBinding
    private var films = mutableListOf<ResultsTop>()
    private lateinit var adapter: TopAdapter
    private var pag = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leng: SharedPreferences = applicationContext.getSharedPreferences("Language", Context.MODE_PRIVATE)
        val idioma = leng.getString("lang", "")

        if(idioma != "english"){
            if (idioma == "spanish"){
                spanish()
            }
            if(idioma == "catalan"){
                catalan()
            }
        }

        listaPeliculas(true)
        binding.tvNumpagPopular.text = "- $pag -"



        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_rank_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_rank_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_rank_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews_a::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
        // Finestra users
        val users1=findViewById<ImageButton>(R.id.bu_rank_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        val menos=findViewById<ImageButton>(R.id.bu_pag_atras)
        menos.setOnClickListener {
            if (pag >1){
                pag = pag -1;
                binding.tvNumpagPopular.text = "- $pag -"
                listaPeliculas(false)
                listaPeliculas(true)
            }
        }

        val mas=findViewById<ImageButton>(R.id.bu_pag_delante)
        mas.setOnClickListener {
            if (pag <10){
                pag = pag +1;
                binding.tvNumpagPopular.text = "- $pag -"
                listaPeliculas(false)
                listaPeliculas(true)
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun listaPeliculas(boolean: Boolean) {
        if(boolean == true){
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(ApiService::class.java)
                    .getPopularFilms("top_rated?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US&page="+pag.toString())

                val peli = call.body()

                runOnUiThread {
                    if (peli != null) {
                        initFilms(peli)
                    }
                }

            }
        }else{
            delFilms()
        }

    }

    private fun initFilms(topFilms: TopFilms){
        for (film in topFilms!!.results){
            films.add(film)
        }
        adapter = TopAdapter(films)
        binding.rvRanking.layoutManager = LinearLayoutManager(this)
        binding.rvRanking.adapter = adapter
    }

    private fun delFilms(){
        films.removeAll { true }
    }

    private fun spanish(){
        binding.tvTitleFrRanking4.text = "PELICULAS POPULARES"
    }

    private fun catalan(){
        binding.tvTitleFrRanking4.text = "PEL??L??CULES POPULARS"
    }
}