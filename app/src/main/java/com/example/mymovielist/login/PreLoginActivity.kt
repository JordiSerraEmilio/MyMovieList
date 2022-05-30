package com.example.mymovielist.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.mymovielist.R
import com.example.mymovielist.login.multilanguage.MultiLanguageActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PreLoginActivity : AppCompatActivity() {
    var tabTitle = arrayOf("Login", "Sign Up")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_login)

        val leng: SharedPreferences = applicationContext.getSharedPreferences("Language", Context.MODE_PRIVATE)
        val idioma = leng.getString("lang", "")

        if(idioma != "english"){
            if (idioma == "spanish"){
                spanish()
            }
            if(idioma == "catalan"){
                catalan()
            }
        }
        var pager = findViewById<ViewPager2>(R.id.viewPager)
        var tab1 = findViewById<TabLayout>(R.id.tabLayout2)
        pager.adapter = PageAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tab1, pager){
                tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        val google=findViewById<ImageButton>(R.id.fbtnGoogle)
        google.setOnClickListener {
            val url = "https://www.themoviedb.org/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        val language=findViewById<ImageButton>(R.id.fbtnFacebook)
        language.setOnClickListener {
            val intento1 = Intent(this, MultiLanguageActivity::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        val twitter=findViewById<ImageButton>(R.id.fbtnTwitter)
        twitter.setOnClickListener {
            val url = "https://twitter.com/themoviedb"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun spanish(){
        findViewById<TextView>(R.id.txtvTitle).text = "Bienvenido a MyMovieList"
    }

    private fun catalan(){
        findViewById<TextView>(R.id.txtvTitle).text = "Benvingut a MyMovieList"
    }
}