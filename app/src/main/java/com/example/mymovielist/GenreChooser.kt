package com.example.mymovielist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import com.example.mymovielist.login.RestApiService
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Genre.Genres
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.usAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class GenreChooser : AppCompatActivity() {

    private lateinit var adapterGetUser: usAdapter
    private lateinit var inputUser: User

    private var list_genres = mutableListOf<String>()
    private var user_genres = mutableListOf<Genres>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre_chooser)

        // region ADD/REMOVE FROM GENRE LIST EVENT
        val tgBttnAction = findViewById<ToggleButton>(R.id.tbttnAction)
        tgBttnAction.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("28")
                var genre = Genres("28", "Action")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("28")
                var genre = Genres("28", "Action")
                user_genres.remove(genre)
            }
        }
        val tgBttnAdventure = findViewById<ToggleButton>(R.id.tbttnAdventure)
        tgBttnAdventure.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("12")
                var genre = Genres("12", "Adventure")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("12")
                var genre = Genres("12", "Adventure")
                user_genres.remove(genre)
            }
        }
        val tgBttnAnimation = findViewById<ToggleButton>(R.id.tbttnAnimation)
        tgBttnAnimation.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("16")
                var genre = Genres("16", "Animation")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("16")
                var genre = Genres("16", "Animation")
                user_genres.remove(genre)
            }
        }
        val tgBttnComedy = findViewById<ToggleButton>(R.id.tbttnComedy)
        tgBttnComedy.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("35")
                var genre = Genres("35", "Comedy")
                user_genres.add(genre)
            }else{ // Eliminar
                var genre = Genres("35", "Comedy")
                user_genres.remove(genre)
            }
        }
        val tgBttnCrime = findViewById<ToggleButton>(R.id.tbttnCrime)
        tgBttnCrime.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("80")
                var genre = Genres("80", "Crime")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("80")
                var genre = Genres("80", "Crime")
                user_genres.remove(genre)
            }
        }
        val tgBttnDocumentary = findViewById<ToggleButton>(R.id.tbttnDocumentary)
        tgBttnDocumentary.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("99")
                var genre = Genres("99", "Documentary")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("99")
                var genre = Genres("99", "Documentary")
                user_genres.remove(genre)
            }
        }
        val tgBttnDrama = findViewById<ToggleButton>(R.id.tbttnDrama)
        tgBttnDrama.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("18")
                var genre = Genres("18", "Drama")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("18")
                var genre = Genres("18", "Drama")
                user_genres.remove(genre)
            }
        }
        val tgBttnFamily = findViewById<ToggleButton>(R.id.tbttnFamily)
        tgBttnFamily.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("10751")
                var genre = Genres("10751", "Family")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("10751")
                var genre = Genres("10751", "Family")
                user_genres.remove(genre)
            }
        }
        val tgBttnFantasy = findViewById<ToggleButton>(R.id.tbttnFantasy)
        tgBttnFantasy.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("14")
                var genre = Genres("14", "Fantasy")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("14")
                var genre = Genres("14", "Fantasy")
                user_genres.remove(genre)
            }
        }
        val tgBttnHistory = findViewById<ToggleButton>(R.id.tbttnHistory)
        tgBttnHistory.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("36")
                var genre = Genres("36", "History")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("36")
                var genre = Genres("36", "History")
                user_genres.remove(genre)
            }
        }
        val tgBttnHorror = findViewById<ToggleButton>(R.id.tbttnHorror)
        tgBttnHorror.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("27")
                var genre = Genres("27", "Horror")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("27")
                var genre = Genres("27", "Horror")
                user_genres.remove(genre)
            }
        }
        val tgBttnMusic = findViewById<ToggleButton>(R.id.tbttnMusic)
        tgBttnMusic.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("10402")
                var genre = Genres("10402", "Music")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("10402")
                var genre = Genres("10402", "Music")
                user_genres.remove(genre)
            }
        }
        val tgBttnMystery = findViewById<ToggleButton>(R.id.tbttnMusic)
        tgBttnMystery.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("9648")
                var genre = Genres("9648", "Mystery")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("9648")
                var genre = Genres("9648", "Mystery")
                user_genres.remove(genre)
            }
        }
        val tgBttnRomance = findViewById<ToggleButton>(R.id.tbttnMusic)
        tgBttnRomance.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("10749")
                var genre = Genres("10749", "Romance")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("10749")
                var genre = Genres("10749", "Romance")
                user_genres.remove(genre)
            }
        }
        val tgBttnScienceFiction = findViewById<ToggleButton>(R.id.tbttnMusic)
        tgBttnScienceFiction.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("878")
                var genre = Genres("878", "Science Fiction")
                user_genres.add(genre)
            }else{ // Eliminar
                var genre = Genres("878", "Science Fiction")
                user_genres.remove(genre)
            }
        }
        val tgBttnTVMovie = findViewById<ToggleButton>(R.id.tbttnMusic)
        tgBttnTVMovie.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("10770")
                var genre = Genres("10770", "TV Movie")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("10770")
                var genre = Genres("10770", "TV Movie")
                user_genres.remove(genre)
            }
        }
        val tgBttnThriller = findViewById<ToggleButton>(R.id.tbttnMusic)
        tgBttnThriller.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("53")
                var genre = Genres("53", "Thriller")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("53")
                var genre = Genres("53", "Thriller")
                user_genres.remove(genre)
            }
        }
        val tgBttnWar = findViewById<ToggleButton>(R.id.tbttnMusic)
        tgBttnWar.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("10752")
                var genre = Genres("10752", "War")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("10752")
                var genre = Genres("10752", "War")
                user_genres.remove(genre)
            }
        }
        val tgBttnWestern = findViewById<ToggleButton>(R.id.tbttnMusic)
        tgBttnWestern.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){ // Añadir
                list_genres.add("37")
                var genre = Genres("37", "Western")
                user_genres.add(genre)
            }else{ // Eliminar
                list_genres.remove("37")
                var genre = Genres("37", "Western")
                user_genres.remove(genre)
            }
        }
        // endregion

        val bttnAddGenres = findViewById<Button>(R.id.bttnAdd)
        bttnAddGenres.setOnClickListener {
            println("########### LISTA ##########   "+list_genres.toString())
            UpdateUserGenres()
        }
    }

    // region AÑADIR LA LISTA DE GENEROS

    private fun UpdateUserGenres(){
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
            var arraylist = ArrayList<Genres>(user_genres)
            inputUser.genres = arraylist

            // Instance of RestApi resources
            val apiService = RestApiService()
            apiService.putuser(email, inputUser){
                if (it?.Id != null){
                    println(it.toString())
                    Toast.makeText(this.applicationContext, "User Genres updated", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this.applicationContext, Recomendedfilms::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this.applicationContext, "Fail updating Genres", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this.applicationContext, "Email not saved in configuration", Toast.LENGTH_SHORT).show()
        }
    }

    // endregion
}