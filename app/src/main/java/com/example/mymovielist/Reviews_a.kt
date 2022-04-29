package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityReviewsBinding
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Review.Reviews
import com.example.mymovielist.models.Review.ReviewsUserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Reviews_a : AppCompatActivity() {

    private lateinit var binding: ActivityReviewsBinding
    private var ur = mutableListOf<Reviews>()
    private lateinit var adapter : ReviewsUserAdapter

    val valor = intent.getStringExtra("usuario")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)

        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_review_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            intent.putExtra("usuario", valor)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_review_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            intent.putExtra("usuario", valor)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra users
        val users1=findViewById<ImageButton>(R.id.bu_review_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            intent.putExtra("usuario", valor)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_review_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            intent.putExtra("usuario", valor)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
    }

    private fun getUserReviews(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun listaReviewsUser(boolean: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getUserReviews().create(ApiService::class.java).getListReviewsUser(""+valor.toString())

            val uRev = call.body()

            runOnUiThread {
                if (uRev != null) {
                       initReviewsUser(uRev)

                }
            }

        }

    }

    private fun initReviewsUser(reviews: List<Reviews>){
        for (review in reviews!!){
            ur.add(review)
       }
        adapter = ReviewsUserAdapter(ur)
        binding.rvReviews.layoutManager = LinearLayoutManager(this)
        binding.rvReviews.adapter = adapter
     }
}