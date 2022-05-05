package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityFilmsYouSeeBinding
import com.example.mymovielist.databinding.ActivityRankingBinding
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.TopFilms.ResultsTop
import com.example.mymovielist.models.TopFilms.TopAdapter
import com.example.mymovielist.models.TopFilms.TopFilms
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.usAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmsYouSee : AppCompatActivity() {

    private lateinit var binding: ActivityFilmsYouSeeBinding
    private lateinit var useradd : User
    private lateinit var adapter: usAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films_you_see)

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_yousee_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_yousee_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews_a::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_yousee_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra users
        val users1=findViewById<ImageButton>(R.id.bu_yousee_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        val prova = findViewById<Button>(R.id.prova)
        prova.setOnClickListener {
            listaPeliculas()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun listaPeliculas() {
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(ApiService::class.java)
                    .getUser("rockyrocky@example.com")

                val user = call.body()

                runOnUiThread {
                    if (user != null) {
                        initFilms(user)

                    }
                }

            }

    }

    private fun initFilms(u: User){
        useradd = u

        adapter = usAdapter(useradd)
        println(useradd.toString())
       // binding.rvRanking.layoutManager = LinearLayoutManager(this)
       // binding.rvRanking.adapter = adapter
    }


}