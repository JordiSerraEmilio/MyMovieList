package com.example.mymovielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mymovielist.models.TopFilms.PageAdapter
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Movies.AdapterCompanies
import com.example.mymovielist.models.Movies.ProductionCompanies
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Movie : AppCompatActivity() {
    var tabTitle = arrayOf("COMPANIES", "ACTORS","REVIEWS")
    private var companies = mutableListOf<ProductionCompanies>()
    private lateinit var adapter: AdapterCompanies
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val idmovie = intent.extras?.getInt("rid")

        bucarPorId(idmovie.toString())

        var pager = findViewById<ViewPager2>(R.id.viewPagerTopFilms)
        var tab1 = findViewById<TabLayout>(R.id.tabs)
        pager.adapter = PageAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tab1, pager){
                tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        //setContent()
    }

    //@SuppressLint("SetTextI18n")
    //private fun setContent(){
    //    var puntos = (intent.extras?.getDouble("rpunts")?.div(2))

    //    findViewById<TextView>(R.id.movie_title).text = intent.extras?.getString("rtitle")
    //    findViewById<TextView>(R.id.tv_film_date).text = "Date: "+intent.extras?.getString("rdate")
    //    findViewById<TextView>(R.id.tv_film_description).text = intent.extras?.getString("rtext")

    //    if (puntos != null) {
    //        findViewById<RatingBar>(R.id.rb_film_cv2).rating = puntos.toFloat()
    //    }

    //}

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun bucarPorId(id: String) {
        val img = findViewById<ImageView>(R.id.movie_image)
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getMevie(id+"?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US")
            val body = call.body()
            if (body != null) {
                runOnUiThread {
                    var imatge = body.backdropPath
                    var titulo = body.title
                    var fecha = body.releaseDate
                    var resumen = body.overview
                    var points = (body.voteAverage?.div(2))
                    Glide.with(applicationContext).load("https://image.tmdb.org/t/p/w500"+imatge).into(img)
                    findViewById<TextView>(R.id.movie_title).text = titulo
                    findViewById<TextView>(R.id.tv_film_date).text = fecha
                    findViewById<TextView>(R.id.tv_film_description).text = resumen
                    if (points != null) {
                        findViewById<RatingBar>(R.id.rb_film_cv2).rating = points.toFloat()
                    }
                    for (x in body.genres) {
                        when (x.id) {
                            "28" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Action"
                            "12" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Adventure"
                            "16" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Animation"
                            "35" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Comedy"
                            "80" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Crime"
                            "99" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Documentary"
                            "18" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Drama"
                            "10751" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Family"
                            "14" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Fantasy"
                            "36" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " History"
                            "27" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Horror"
                            "10402" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Music"
                            "9648" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Mystery"
                            "10749" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Romance"
                            "878" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Science Fiction"
                            "10770" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " TV Movie"
                            "53" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Thriller"
                            "10752" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " War"
                            "37" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Western"
                            else -> { // Note the block
                                findViewById<TextView>(R.id.tv_film_genres).text =
                                    findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Undefined genre"
                            }
                        }
                    }
                    if(body.productionCompanies != null){
                        initCompanies(body.productionCompanies)
                    }
                }
            }
        }
    }

    private fun initCompanies(Companies : List<ProductionCompanies>){
        for (compani in Companies!!){
            companies.add(compani)
        }
        adapter = AdapterCompanies(companies)
        findViewById<RecyclerView>(R.id.rv_companies).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rv_companies).adapter = adapter
    }

}