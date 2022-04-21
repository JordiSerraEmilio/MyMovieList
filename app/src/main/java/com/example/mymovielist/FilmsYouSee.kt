package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class FilmsYouSee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films_you_see)

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_yousee_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
            finish();
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_yousee_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews::class.java)
            startActivity(intento1)
            finish();
        }

        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_yousee_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
            finish();
        }

        // Finestra users
        val users1=findViewById<ImageButton>(R.id.bu_yousee_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            startActivity(intento1)
            finish();
        }

    }
}