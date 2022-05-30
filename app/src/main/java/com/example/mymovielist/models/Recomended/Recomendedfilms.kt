package com.example.mymovielist.models.Recomended

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.*
import com.example.mymovielist.databinding.ActivityRankingBinding
import com.example.mymovielist.databinding.ActivityRecomendedfilmsBinding
import com.example.mymovielist.login.RestApiService
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.TopFilms.ResultsTop
import com.example.mymovielist.models.TopFilms.TopAdapter
import com.example.mymovielist.models.TopFilms.TopFilms
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.usAdapter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Recomendedfilms : AppCompatActivity() {

    private lateinit var binding: ActivityRecomendedfilmsBinding
    private lateinit var adapter: TopAdapter
    private lateinit var adapterGetUser: usAdapter
    private  var inputUser = User()

    private var lGenresStr = ""
    private var films = mutableListOf<ResultsTop>()

    private var orderBy = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecomendedfilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leng: SharedPreferences = applicationContext.getSharedPreferences("Language", Context.MODE_PRIVATE)
        val idioma = leng.getString("lang", "")
        val spinner = binding.spinner
        var spinList = listOf("Descendant", "Ascendant", "Genres")

        if(idioma != "english"){
            if (idioma == "spanish"){
                spanish()
                spinList = listOf("Descendiente", "Ascendente", "Generos")
            }
            if(idioma == "catalan"){
                catalan()
                spinList = listOf("Descendent", "Ascendent", "Generes")
            }
        }

        val spinAdapter = ArrayAdapter(this, R.layout.custom_selecteddropdown, spinList)
        spinAdapter.setDropDownViewResource(R.layout.custom_dropdown)
        spinner.adapter = spinAdapter
        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0){
                    orderBy = "desc"
                    GetUser()
                }else if(p2 == 1){
                    orderBy = "asc"
                    GetUser()
                }else{
                    orderBy = "desc"
                    GetUser()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        // region MENU

        // Finestra Films you see
        val yousee1 = findViewById<ImageButton>(R.id.bu_films_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra reviews
        val review1 = findViewById<ImageButton>(R.id.bu_films_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews_a::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
        // Finestra users
        val users1 = findViewById<ImageButton>(R.id.bu_films_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra ranking
        val rank1 = findViewById<ImageButton>(R.id.bu_films_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, Ranking::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
        // endregion
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/discover/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    private fun initFilms(topFilms: TopFilms) {
        for (film in topFilms!!.results) {
            films.add(film)
        }
        adapter = TopAdapter(films)
        binding.rvFilms.layoutManager = LinearLayoutManager(this)
        binding.rvFilms.adapter = adapter
    }

    private fun delFilms() {
        films.removeAll { true }
    }

    private fun GetUser(){
        val shared: SharedPreferences = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = shared.getString("email", "")
        if (email != null) {
            gettingUser(email)
        }
    }

    private fun getRetrofitUserByEmail(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun gettingUser(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofitUserByEmail().create(ApiService::class.java)
                .getUser(email)

            val user = call.body()

            runOnUiThread {
                if (user != null) {
                    initUser(user, email)
                }
            }
        }
    }
    private fun initUser(u: User, email: String){
        inputUser = u
        for(genre in inputUser.genres){
            lGenresStr += genre.id+","
        }
        lGenresStr = lGenresStr.dropLast(1)
        adapterGetUser = usAdapter(inputUser)

        CoroutineScope(Dispatchers.IO).launch {
            var call = getRetrofit().create(ApiService::class.java)
                .getPopularFilms("movie?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US&sort_by=popularity."+orderBy+"&include_adult=false&include_video=false&page=1&with_genres="+lGenresStr+"&with_watch_monetization_types=flatrate")

            val peli = call.body()
            runOnUiThread {
                initFilms(peli!!)
            }
            val handler = CoroutineExceptionHandler { _, exception ->
                println("CoroutineExceptionHandler got $exception")
            }
        }
    }

    private fun spanish(){
        binding.tvTitleFrRanking3.text = "PELICULAS"
    }

    private fun catalan(){
        binding.tvTitleFrRanking3.text = "PEL·LICULES"
    }
}