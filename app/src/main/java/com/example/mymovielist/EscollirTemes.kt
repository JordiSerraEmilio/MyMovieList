package com.example.mymovielist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymovielist.databinding.ActivityEscollirTemesBinding
import com.example.mymovielist.login.RestApiService
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Genre.GenreAdapter
import com.example.mymovielist.models.Genre.Genres
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.usAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class EscollirTemes : AppCompatActivity() {
    private lateinit var binding: ActivityEscollirTemesBinding
    private lateinit var adapter: GenreAdapter
    private lateinit var adapterGetUser: usAdapter

    private var generos = mutableListOf<Genres>()

    private var user_genres = ArrayList<Genres>()
    private lateinit var inputUser: User


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEscollirTemesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initRecyclerView()
        buscarGeneros()

        //
        binding.bttnAddGenres.setOnClickListener {
            AddGenresToUser()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/genre/movie/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun buscarGeneros() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getGenresList("list?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=us-US")
            val genre = call.body()?.genres

            runOnUiThread {
                if (genre != null) {
                    initGenres(genre)
                }
            }
        }
    }

    private fun initGenres(genres: List<Genres>?) {
        for (genre in genres!!) {
            generos.add(genre)
        }
        adapter = GenreAdapter(generos, user_genres)
        binding.rvChooseGenres.layoutManager = GridLayoutManager(this, 3)
        binding.rvChooseGenres.adapter = adapter

    }

    //

    private fun AddGenresToUser() {
        //Coge la lista del adapter y hace un PUT añadiendole la lista
//        user_genres = intent.extras?.getParcelable<Genres>("data")
//        Toast.makeText(applicationContext, user_genres.toString(), Toast.LENGTH_LONG).show()



        // TESTING
        // Sacar el email guardado en el SharedPreferences para hacer el Get de ese usuario
//        val shared: SharedPreferences = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
//        val email = shared.getString("email", "")
//
//        val apiService = RestApiService()
//
//        //IMPLEMENTAR
//        //var user = getUser
//        println(email)
//
//        if (email != null) {
//            gettingUser(email)
//            println("################# GETTED "+gettedUser)
//            apiService.putuser(email, gettedUser){
//                if (it?.Id != null){
//
//                    Toast.makeText(this.applicationContext, "Genres added", Toast.LENGTH_SHORT).show()
//
//                    val intent = Intent(this.applicationContext, Recomendedfilms::class.java)
//                    startActivity(intent)
//
//                }else{
//                    Toast.makeText(this.applicationContext, "Fail adding genres", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }else{
//            Toast.makeText(this.applicationContext, "Email not saved in configuration", Toast.LENGTH_SHORT).show()
//        }


        val shared: SharedPreferences = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = shared.getString("email", "")
//        val list = intent.getParcelableExtra<Genres>("user_genres")
//        println(list.toString())
        if (email != null) {
            gettingUser(email)
        }
    }

    //region GET USER

    private fun getRetrofitUserByEmail(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun gettingUser(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofitUserByEmail().create(ApiService::class.java)
                .getUser(email)

            val user = call.body()

            runOnUiThread {
                if (user != null) {
                    initUser(user, email)
                }
            }
        }
    }

    private fun initUser(u: User, email: String){
        inputUser = u
        adapterGetUser = usAdapter(inputUser)

        // PUT METHOD
        if (email != null) {

            // Setting genre list to the user retrived from api

            inputUser.genres = user_genres

            // Instance of RestApi resources
            val apiService = RestApiService()
            apiService.putuser(email, inputUser){
                if (it?.Id != null){
                    println(it.toString())
                    Toast.makeText(this.applicationContext, "Genres added", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this.applicationContext, Recomendedfilms::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this.applicationContext, "Fail adding genres", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this.applicationContext, "Email not saved in configuration", Toast.LENGTH_SHORT).show()
        }
    }

    //endregion



    //
}