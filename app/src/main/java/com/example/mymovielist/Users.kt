package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_users_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
        }

        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_users_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
        }

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_users_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, Ranking::class.java)
            startActivity(intento1)
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_users_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews::class.java)
            startActivity(intento1)
        }
    }
}