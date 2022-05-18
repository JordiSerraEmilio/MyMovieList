package com.example.mymovielist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovielist.databinding.ActivityUsersBinding
import com.example.mymovielist.login.PreLoginActivity
import com.example.mymovielist.login.RestApiService
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.Users.ChooseImageUserActivity
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.UserAdapter
import com.example.mymovielist.models.Users.usAdapter
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

    private lateinit var adapterGetUser: usAdapter
    private lateinit var inputUser: User
    private var inputName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seBuscaUsuari.setOnQueryTextListener(this)

        llistaUsers(false,"")


        // Boton para cerrar sessión
        val closeSession = findViewById<ImageButton>(R.id.bttnCloseSession)
        closeSession.setOnClickListener {
            val shared: SharedPreferences = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
            val edit = shared.edit()
            edit.putString("email", "")
            edit.commit()

            val intent = Intent(this, PreLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Cambiar el nombre del usuario
        val editTxtName = findViewById<EditText>(R.id.editTxtUserName)
        editTxtName.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus){
                inputName = editTxtName.text.toString()
                UpdateUserName()
            }
        }

        // Cambiar foto
        val imgBttn = findViewById<ImageButton>(R.id.imgBttnUserImage)
        imgBttn.setOnClickListener {
            val intent = Intent(this, ChooseImageUserActivity::class.java)
            startActivity(intent)
        }

        // Actualizar perfil
        val bttnUpdate = findViewById<Button>(R.id.bttnUpdateProfile)
        bttnUpdate.setOnClickListener {
            editTxtName.clearFocus()
        }

        // region MENU
        // Finestra pelicules
        val films1=findViewById<ImageButton>(R.id.bu_users_films)
        films1.setOnClickListener {
            val intento1 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra Films you see
        val yousee1=findViewById<ImageButton>(R.id.bu_users_yousee)
        yousee1.setOnClickListener {
            val intento1 = Intent(this, FilmsYouSee::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra ranking
        val rank1=findViewById<ImageButton>(R.id.bu_users_rank)
        rank1.setOnClickListener {
            val intento1 = Intent(this, Ranking::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }

        // Finestra reviews
        val review1=findViewById<ImageButton>(R.id.bu_users_review)
        review1.setOnClickListener {
            val intento1 = Intent(this, Reviews_a::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
        // endregion
        binding.seBuscaUsuari.setOnClickListener {
            binding.seBuscaUsuari.isIconified = false
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


            var call: Response<List<User>>

            if(boolean == true){
                 call = getUsersFiltre().create(ApiService::class.java)
                    .getListUsers("{searchName}?search_name=$query")

            }else{
                call = getUsers().create(ApiService::class.java)
                    .getListUsers("users")
            }


            val user = call.body()

            users.removeAll { true }

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

        //hideKeyboard()
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


    //private fun hideKeyboard() {
    //    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    //    imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    //}


    // region CAMBIAR NOMBRE

    private fun UpdateUserName(){
        val shared: SharedPreferences = applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = shared.getString("email", "")
        if (email != null) {
            gettingUser(email)
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

            // Cambiar el nombre de usuario
            inputUser.name = inputName

            // Instance of RestApi resources
            val apiService = RestApiService()
            apiService.putuser(email, inputUser){
                if (it?.Id != null){
                    println(it.toString())
                    Toast.makeText(this.applicationContext, "User name updated", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this.applicationContext, Users::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    Toast.makeText(this.applicationContext, "Fail updating username", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this.applicationContext, "Email not saved in configuration", Toast.LENGTH_SHORT).show()
        }
    }

    // endregion
}