package com.example.mymovielist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityReviewsBinding
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.Review.ReviewUserAdapter
import com.example.mymovielist.models.Review.Reviews
import com.example.mymovielist.models.Users.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Reviews_a : AppCompatActivity() {

    private lateinit var binding: ActivityReviewsBinding
    private var reviewsUser = mutableListOf<Reviews>()
    private lateinit var adapter: ReviewUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaReviewsUser()

        // region MENU
        // Finestra pelicules
        val films1 = findViewById<ImageButton>(R.id.bu_review_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra Films you see
        val yousee1 = findViewById<ImageButton>(R.id.bu_review_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra users
        val users1 = findViewById<ImageButton>(R.id.bu_review_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra ranking
        val rank1 = findViewById<ImageButton>(R.id.bu_review_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, Ranking::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // endregion
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun listaReviewsUser() {
        val shared: SharedPreferences = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val edit = shared.edit()
        edit.putString("email", "rockyrockyUPDATED@example.com")
        edit.commit()
        val email = shared.getString("email", "")

        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getUser(email!!)

            val user = call.body()

            runOnUiThread {
                if (user != null) {
                    initReviewsUser(user)

                }
            }

        }

    }

    private fun initReviewsUser(user: User) {
        for (u in user!!.reviews) {
            reviewsUser.add(u)
            println(u)
        }
        adapter = ReviewUserAdapter(reviewsUser)
        binding.rvReviewsUser.layoutManager = LinearLayoutManager(this)
        binding.rvReviewsUser.adapter = adapter
    }
}