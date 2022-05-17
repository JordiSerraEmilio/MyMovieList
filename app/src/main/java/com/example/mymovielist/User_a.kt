package com.example.mymovielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Users.PageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class User_a : AppCompatActivity() {

    var tabTitle = arrayOf("REVIEWS", "SEE IT", "FAVOURITE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val mailuser = intent.extras?.getString("uCorreo")
        BuscarPorCorreo(mailuser.toString())

        var pager = findViewById<ViewPager2>(R.id.viewPagerUser)
        var tab1 = findViewById<TabLayout>(R.id.tabLayout)
        pager.adapter = PageAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tab1, pager){
                tab, position ->
            tab.text = tabTitle[position]
        }.attach()

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

}