package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Ranking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_rank_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
        }

        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_rank_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_rank_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews::class.java)
            startActivity(intento1)
        }
        // Finestra users
        val users1=findViewById<ImageButton>(R.id.bu_rank_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            startActivity(intento1)
        }
    }
}