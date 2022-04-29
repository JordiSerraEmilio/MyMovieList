package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Recomendedfilms : AppCompatActivity() {

    private var valor = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendedfilms)

        valor = intent.getStringExtra("usuario").toString()


        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_films_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            intent.putExtra("usuario", valor)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_films_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews_a::class.java)
            intent.putExtra("usuario", valor)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
        // Finestra users
        val users1=findViewById<ImageButton>(R.id.bu_films_users)
        users1.setOnClickListener {
            val intento1 = Intent(this, Users::class.java)
            intent.putExtra("usuario", valor)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_films_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, Ranking::class.java)
            intent.putExtra("usuario", valor)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
    }
}