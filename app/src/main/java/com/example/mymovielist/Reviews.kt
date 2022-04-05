package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Reviews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)

        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_review_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
        }

        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_review_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
        }

        // Finestra users
        val users1=findViewById<ImageButton>(R.id.bu_review_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            startActivity(intento1)
        }

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_review_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
        }
    }
}