package com.example.mymovielist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.databinding.ActivityFilmsYouSeeBinding
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.Users.Seen
import com.example.mymovielist.models.Users.SeenAdapter
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.usAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmsYouSee : AppCompatActivity() {

    var i = 1;
    private lateinit var binding: ActivityFilmsYouSeeBinding
    private lateinit var useradd : User
    private var watchedfilms = mutableListOf<Seen>()
    private var seenfilms = mutableListOf<Seen>()
    private lateinit var adapter: SeenAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsYouSeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared: SharedPreferences = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val edit = shared.edit()
        val email = shared.getString("email", "")

        CanviarRecycleView(email.toString())

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_yousee_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, Ranking::class.java)
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

        val b1 = findViewById<TextView>(R.id.tv_seen_yousee)
        b1.setOnClickListener {
            if(i != 1) {
                if (i == 2) {
                    watchedfilms.removeAll { true }
                }
                i = 1
                CanviarRecycleView(email.toString())
            }
        }

        val b2=findViewById<TextView>(R.id.tv_watched_yousee)
        b2.setOnClickListener {
            if(i != 2){
                if(i == 1){
                    seenfilms.removeAll {true}
                }
                i = 2
                CanviarRecycleView(email.toString())
            }
        }

//        val prova = findViewById<Button>(R.id.prova)
//        prova.setOnClickListener {
//            listaPeliculas()
//        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun CanviarRecycleView(correo: String) {
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(ApiService::class.java)
                    .getUser(correo+"")

                val body = call.body()

                if(i == 1){
                    runOnUiThread {
                        if (body != null) {
                            initSeen(body)
                        }
                    }
                }
                else if(i == 2){

                    if (body != null) {
                        runOnUiThread {
                            initWatched(body)
                        }

                    }

                }

            }

    }

    private fun initSeen(user: User?) {
        for (u in user!!.seen){
            seenfilms.add(u)
        }

        adapter = SeenAdapter(seenfilms)
        binding.rvFilmlist.layoutManager = LinearLayoutManager(this)
        binding.rvFilmlist.adapter = adapter
    }

    private fun initWatched(user: User?) {
        for (u in user!!.toWatch){
            watchedfilms.add(u)
        }

        adapter = SeenAdapter(watchedfilms)
        binding.rvFilmlist.layoutManager = LinearLayoutManager(this)
        binding.rvFilmlist.adapter = adapter
    }


}