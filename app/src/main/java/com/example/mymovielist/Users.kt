package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityUsersBinding
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.TopFilms.TopAdapter
import com.example.mymovielist.models.TopFilms.TopFilms
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Users : AppCompatActivity() , SearchView.OnQueryTextListener{

    private lateinit var binding: ActivityUsersBinding
    private var users = mutableListOf<User>()
    private lateinit var adapter : UserAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seBuscaUsuari.setOnQueryTextListener(this)

        llistaUsers(false,"")

        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_users_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
            finish();
        }

        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_users_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
            finish();
        }

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_users_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, Ranking::class.java)
            startActivity(intento1)
            finish();
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_users_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews::class.java)
            startActivity(intento1)
            finish();
        }
    }

    private fun getUsers(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getUsersFiltre(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/usersnames/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun llistaUsers(boolean: Boolean, query:String) {
        CoroutineScope(Dispatchers.IO).launch {

            users.removeAll { true }

            var call: Response<List<User>>

            if(boolean == true){
                 call = getUsersFiltre().create(ApiService::class.java)
                    .getListUsers("{searchName}?search_name=$query")

            }else{
                call = getUsers().create(ApiService::class.java)
                    .getListUsers("users")
            }


            val user = call.body()

            runOnUiThread {
                if (user != null) {
                    initUsers(user)
                }
            }
        }
    }

    private fun searchByName(query:String){

        if(!query.isBlank() || !query.isEmpty()){
            llistaUsers(true, query)
        }else{
            llistaUsers(false, "")
        }

        hideKeyboard()
    }


    private fun initUsers(user: List<User>){
        for (u in user!!){
            users.add(u)
        }
        adapter = UserAdapter(users)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null) {
            searchByName(query)
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {

        if(query != null) {
            searchByName(query)
        }else if(query.isNullOrBlank()){
            llistaUsers(false, "")
        }


        return true
    }


    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}