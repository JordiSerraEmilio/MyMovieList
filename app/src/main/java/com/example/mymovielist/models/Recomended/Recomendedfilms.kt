package com.example.mymovielist.models.Recomended

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.*
import com.example.mymovielist.databinding.ActivityRankingBinding
import com.example.mymovielist.databinding.ActivityRecomendedfilmsBinding
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.TopFilms.ResultsTop
import com.example.mymovielist.models.TopFilms.TopAdapter
import com.example.mymovielist.models.TopFilms.TopFilms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Recomendedfilms : AppCompatActivity() {
    private lateinit var binding : ActivityRecomendedfilmsBinding
    private lateinit var adapter: TopAdapter
    private var pags = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    private var pag = 1
    private var films = mutableListOf<ResultsTop>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendedfilmsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_recomendedfilms)

//        for (x in pags){
//            pag = x
//            listaPeliculas(true)
//        }
        listaPeliculas(true)
        println(films.toString())

        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_films_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_films_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews_a::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
        // Finestra users
        val users1=findViewById<ImageButton>(R.id.bu_films_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_films_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, Ranking::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
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
        binding.rvFilms.layoutManager = LinearLayoutManager(this)
        binding.rvFilms.adapter = adapter
    }

    private fun delFilms(){
        films.removeAll { true }
    }

}