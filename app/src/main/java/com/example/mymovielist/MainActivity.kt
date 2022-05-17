package com.example.mymovielist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.example.mymovielist.login.PreLoginActivity
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.onboardingscreen.OnBoardingMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    lateinit var apiUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiUser = User()

        // Instancia del shared
        val shared: SharedPreferences =
            applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)

        if (shared != null) { // Comprovación por si acaso peta
            val email = shared.getString("email", "") // Intenta coger el valor email
            if (email != "") { // Si hay email, por lo tanto ha creado cuenta o ha iniciado anteriormente...
                gettingUser(email!!) // Assigna a apiUser el usuario guardado en la API
                if (apiUser.isLogged == 0) { // Si apiUser no estaba logeado previamente...
                    handler = Handler()
                    handler.postDelayed({
                        val intent = Intent(this, OnBoardingMain::class.java)
                        startActivity(intent)
                        finish()
                    }, 2000)
                } else { // Si ya estaba logeado previamente...
                    handler = Handler()
                    handler.postDelayed({
                        val intent = Intent(this, Recomendedfilms::class.java)
                        startActivity(intent)
                        finish()
                    }, 2000)
                }
            } else {
                handler = Handler()
                handler.postDelayed({
                    val intent = Intent(this, OnBoardingMain::class.java)
                    startActivity(intent)
                    finish()
                }, 2000)
            }
        }

    }


    private fun getRetrofitUserByEmail(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun gettingUser(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofitUserByEmail().create(ApiService::class.java)
                .getUser(email)

            apiUser = call.body()!!
        }
    }
}