package com.example.mymovielist

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Users.SeenAdapter
import com.example.mymovielist.models.Review.ReviewUserAdapter
import com.example.mymovielist.models.Review.Reviews
import com.example.mymovielist.models.Users.Seen
import com.example.mymovielist.models.Users.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class User_a : AppCompatActivity() {

    //var tabTitle = arrayOf("REVIEWS", "SEE IT", "FAVOURITE")

    var i = 1;
    private var reviewsUser = mutableListOf<Reviews>()
    private var watchedfilms = mutableListOf<Seen>()
    private var seenfilms = mutableListOf<Seen>()
    private lateinit var adapter: ReviewUserAdapter
    private lateinit var adapter2: SeenAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

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

        val mailuser = intent.extras?.getString("uCorreo")
        BuscarPorCorreo(mailuser.toString())
        CanviarRecycleView(mailuser.toString())

        val rank1 = findViewById<TextView>(R.id.tv_bu_favorite_user)
        rank1.setOnClickListener {
            if(i != 1) {
                if (i == 2) {
                    seenfilms.removeAll { true }
                } else if (i == 3) {
                    reviewsUser.removeAll { true }
                }
                i = 1
                CanviarRecycleView(mailuser.toString())
            }
        }

        val rank2=findViewById<TextView>(R.id.tv_bu_watched_user)
        rank2.setOnClickListener {
            if(i != 2){
                if(i == 1){
                    watchedfilms.removeAll {true}
                }else if(i == 3){
                    reviewsUser.removeAll { true }
                }
                i = 2
                CanviarRecycleView(mailuser.toString())
            }
        }

        val rank3 = findViewById<TextView>(R.id.tv_bu_reviews_user)
        rank3.setOnClickListener {
            if(i != 3) {
                if (i == 1) {
                    watchedfilms.removeAll {true}
                } else if (i == 2) {
                    seenfilms.removeAll { true }
                }
                i = 3
                CanviarRecycleView(mailuser.toString())
            }
        }

        //var pager = findViewById<ViewPager2>(R.id.viewPagerUser)
        //var tab1 = findViewById<TabLayout>(R.id.tabLayout)
        //pager.adapter = PageAdapter(supportFragmentManager, lifecycle)
        //TabLayoutMediator(tab1, pager){
        //        tab, position ->
        //    tab.text = tabTitle[position]
        //}.attach()

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun BuscarPorCorreo(correo: String){
        val img = findViewById<ImageView>(R.id.user_imatge)
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getUser(correo+"")
            val body = call.body()
            if (body != null) {
                runOnUiThread {
                    var imatge : String?
                    if(body.image.toString().equals("")){
                        imatge = "https://icones.pro/wp-content/uploads/2021/02/icone-utilisateur-orange.png"
                    }else{
                        imatge = body.image
                    }
                    var nomUser = body.name
                    var correoUser = body.email
                    Glide.with(applicationContext).load(imatge).into(img)
                    findViewById<TextView>(R.id.user_name).text = nomUser
                    findViewById<TextView>(R.id.user_mail).text = "Mail: "+correoUser
                }
            }
        }
    }

    private fun CanviarRecycleView(correo: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getUser(correo+"")
            val body = call.body()

            if(i == 1){
                runOnUiThread {
                    if (body != null) {
                        initWatched(body)
                    }
                }
            }
            else if(i == 2){

                if (body != null) {
                    runOnUiThread {
                        initSeen(body)
                    }

                }

            }
            else if(i == 3){
                runOnUiThread {
                    if (body != null) {
                        initReviewsUser(body)
                    }
                }
            }
        }
    }

    private fun initReviewsUser(user: User?) {
        adapter = ReviewUserAdapter(user!!)
        findViewById<RecyclerView>(R.id.rv_user_act).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rv_user_act).adapter = adapter
    }

    private fun initSeen(user: User?) {
        for (u in user!!.seen){
            seenfilms.add(u)
        }

        adapter2 = SeenAdapter(seenfilms)
        findViewById<RecyclerView>(R.id.rv_user_act).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rv_user_act).adapter = adapter2
    }

    private fun initWatched(user: User?) {
        for (u in user!!.toWatch){
            watchedfilms.add(u)
        }

        adapter2 = SeenAdapter(watchedfilms)
        findViewById<RecyclerView>(R.id.rv_user_act).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rv_user_act).adapter = adapter2
    }

    private fun spanish(){
        findViewById<TextView>(R.id.tv_bu_favorite_user).text = "POR VER"
        findViewById<TextView>(R.id.tv_bu_watched_user).text = "VISTAS"
        findViewById<TextView>(R.id.tv_bu_reviews_user).text = "RESE??AS"
    }

    private fun catalan(){
        findViewById<TextView>(R.id.tv_bu_favorite_user).text = "PER VEURE"
        findViewById<TextView>(R.id.tv_bu_watched_user).text = "VISTES"
        findViewById<TextView>(R.id.tv_bu_reviews_user).text = "RESSENYES"
    }

}