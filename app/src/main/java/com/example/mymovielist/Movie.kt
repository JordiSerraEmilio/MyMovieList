package com.example.mymovielist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mymovielist.login.RestApiService
import com.example.mymovielist.models.Actors.AdapterActor
import com.example.mymovielist.models.Actors.Cast
import com.example.mymovielist.models.TopFilms.PageAdapter
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Genre.Genres
import com.example.mymovielist.models.Movies.AdapterCompanies
import com.example.mymovielist.models.Movies.ProductionCompanies
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.Review.CreateReviewActivity
import com.example.mymovielist.models.Review.ReviewFilmsAdapter
import com.example.mymovielist.models.TopFilms.ResultsTop
import com.example.mymovielist.models.Users.Seen
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.usAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Movie : AppCompatActivity() {

    //var tabTitle = arrayOf("COMPANIES", "ACTORS","REVIEWS")
    var i = 1

    private var companies = mutableListOf<ProductionCompanies>()
    private var actores = mutableListOf<Cast>()
    private var users = mutableListOf<User>()
    private var idmovie = 0
//    private var isseen = false

    private lateinit var adapter: AdapterCompanies
    private lateinit var adapter2: AdapterActor
    private lateinit var adapter3: ReviewFilmsAdapter

    private lateinit var adapterGetUser: usAdapter
    private var inputUser = User()
    private var seenList = mutableListOf<Seen>()
    private var seenListNew = mutableListOf<Seen>()
    private var toWatchList = mutableListOf<Seen>()
    private var toWatchListNew = mutableListOf<Seen>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        idmovie = intent.extras?.getInt("rid")!!
        bucarPorId(idmovie.toString())
        CanviarRecycleView(idmovie.toString())

        val rank1 = findViewById<TextView>(R.id.tv_bu_actors)
        rank1.setOnClickListener {
            if (i != 2) {
                if (i == 1) {
                    companies.removeAll { true }
                } else if (i == 3) {
                    users.removeAll { true }
                }
                i = 2
                CanviarRecycleView(idmovie.toString())
            }
        }

        val rank2 = findViewById<TextView>(R.id.tv_bu_producers)
        rank2.setOnClickListener {
            if (i != 1) {
                if (i == 2) {
                    actores.removeAll { true }
                } else if (i == 3) {
                    users.removeAll { true }
                }
                i = 1
                CanviarRecycleView(idmovie.toString())
            }
        }

        val rank3 = findViewById<TextView>(R.id.tv_bu_reviews)
        rank3.setOnClickListener {
            if (i != 3) {
                if (i == 1) {
                    companies.removeAll { true }
                } else if (i == 2) {
                    actores.removeAll { true }
                }
                i = 3
                CanviarRecycleView(idmovie.toString())
            }
        }

        //
        val chkBoxSeenList = findViewById<CheckBox>(R.id.chkBoxSeen)
        chkBoxSeenList.setOnClickListener {
            if (chkBoxSeenList.isChecked) { // Si esta checkeado = quiere añadirlo a la lista correspondiente
                UpdateUserAddListSeen()
            } else {
                UpdateUserRemoveListSeen()
            }
        }
        val chkBoxToWatchList = findViewById<CheckBox>(R.id.chkBoxToWatch)
        chkBoxToWatchList.setOnClickListener {
            if (chkBoxToWatchList.isChecked) { // Si esta checkeado = quiere añadirlo a la lista correspondiente
                UpdateUserAddListToWatch()
            } else {
                UpdateUserRemoveListToWatch()
            }
        }

        val bttnCreateReview = findViewById<Button>(R.id.bttnCreateReview)
        bttnCreateReview.setOnClickListener {
            val intent = Intent(applicationContext, CreateReviewActivity::class.java)
            intent.putExtra("movieid", idmovie)
            startActivity(intent)
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getRetrofit2(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    private fun getRetrofit3(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun CanviarRecycleView(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getMevie(id + "?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US")
            val call2 = getRetrofit().create(ApiService::class.java)
                .getActors(id + "/credits?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US")
            val call3 = getRetrofit2().create(ApiService::class.java)
                .getListUsers("users")
            val body = call.body()
            val body2 = call2.body()
            val body3 = call3.body()



            if (i == 1) {
                if (body != null) {
                    runOnUiThread {
                        if (body.productionCompanies != null) {
                            initCompanies(body.productionCompanies)
                        }
                    }
                }
            } else if (i == 2) {
                if (body2 != null) {
                    runOnUiThread {
                        if (body2 != null) {
                            initActors(body2.cast)
                        }
                    }
                }
            } else if (i == 3) {
                if (body3 != null) {
                    runOnUiThread {
                        if (body != null) {
                            initReviews(body3, body.id.toString())
                        }
                    }
                }
            }


        }
    }

    private fun initCompanies(Companies: List<ProductionCompanies>) {
        for (compani in Companies!!) {
            companies.add(compani)
        }
        adapter = AdapterCompanies(companies)
        findViewById<RecyclerView>(R.id.rv_film).layoutManager =
            LinearLayoutManager(this.baseContext)
        findViewById<RecyclerView>(R.id.rv_film).adapter = adapter
    }

    private fun initActors(Actors: List<Cast>) {
        for (act in Actors!!) {
            actores.add(act)
        }
        adapter2 = AdapterActor(actores)

        findViewById<RecyclerView>(R.id.rv_film).layoutManager =
            LinearLayoutManager(this.baseContext)
        findViewById<RecyclerView>(R.id.rv_film).adapter = adapter2
    }

    private fun initReviews(Users: List<User>, id: String?) {
        for (us in Users!!) {
            for (re in us.reviews) {
                if (re.movieId == id) {
                    users.add(us)

                }
            }
        }
        adapter3 = ReviewFilmsAdapter(users, id.toString())

        findViewById<RecyclerView>(R.id.rv_film).layoutManager =
            LinearLayoutManager(this.baseContext)
        findViewById<RecyclerView>(R.id.rv_film).adapter = adapter3
    }

    private fun bucarPorId(id: String) {
        val shared: SharedPreferences =
            applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = shared.getString("email", "")

        val img = findViewById<ImageView>(R.id.movie_image)
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getMevie(id + "?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US")
            val call2 = getRetrofit3().create(ApiService::class.java)
                .getUser(email+"")
            val body = call.body()
            val body2 = call2.body()
            if (body != null) {
                runOnUiThread {
                    var imatge = body.backdropPath
                    var titulo = body.title
                    var fecha = body.releaseDate
                    var resumen = body.overview
                    var points = (body.voteAverage?.div(2))
                    Glide.with(applicationContext).load("https://image.tmdb.org/t/p/w500" + imatge)
                        .into(img)
                    findViewById<TextView>(R.id.movie_title).text = titulo
                    findViewById<TextView>(R.id.tv_film_date).text = fecha
                    findViewById<TextView>(R.id.tv_film_description).text = resumen
                    if (points != null) {
                        findViewById<RatingBar>(R.id.rb_film_cv2).rating = points.toFloat()
                    }
                    for (x in body.genres) {
                        when (x.id) {
                            "28" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Action"
                            "12" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Adventure"
                            "16" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Animation"
                            "35" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Comedy"
                            "80" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Crime"
                            "99" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Documentary"
                            "18" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Drama"
                            "10751" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Family"
                            "14" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Fantasy"
                            "36" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " History"
                            "27" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Horror"
                            "10402" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Music"
                            "9648" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Mystery"
                            "10749" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Romance"
                            "878" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Science Fiction"
                            "10770" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " TV Movie"
                            "53" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Thriller"
                            "10752" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " War"
                            "37" -> findViewById<TextView>(R.id.tv_film_genres).text =
                                findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Western"
                            else -> { // Note the block
                                findViewById<TextView>(R.id.tv_film_genres).text =
                                    findViewById<TextView>(R.id.tv_film_genres).text.toString() + " Undefined genre"
                            }
                        }
                    }
                }
            }
            if (body2 != null){
                runOnUiThread {
                    for(item in body2.seen){
                        if(item.movieId == idmovie.toString()){
                            println("################################ "+idmovie)
                            findViewById<CheckBox>(R.id.chkBoxSeen).isChecked = true
                        }
                    }
                }
            }
            if (body2 != null){
                runOnUiThread {
                    for(item in body2.toWatch){
                        if(item.movieId == idmovie.toString()){
                            println("################################ "+idmovie)
                            findViewById<CheckBox>(R.id.chkBoxToWatch).isChecked = true
                        }
                    }
                }
            }
        }
    }

    private fun UpdateUserAddListSeen() {
        val shared: SharedPreferences =
            applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = shared.getString("email", "")
        if (email != null) {
            gettingUserAddListSeen(email)
        }
    }

    private fun UpdateUserAddListToWatch() {
        val shared: SharedPreferences =
            applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = shared.getString("email", "")
        if (email != null) {
            gettingUserAddListToWatch(email)
        }
    }

    private fun UpdateUserRemoveListSeen() {
        val shared: SharedPreferences =
            applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = shared.getString("email", "")
        if (email != null) {
            gettingUserRemoveListSeen(email)
        }
    }

    private fun UpdateUserRemoveListToWatch() {
        val shared: SharedPreferences =
            applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = shared.getString("email", "")
        if (email != null) {
            gettingUserRemoveListToWatch(email)
        }
    }

    private fun getRetrofitUserByEmail(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun gettingUserAddListSeen(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofitUserByEmail().create(ApiService::class.java)
                .getUser(email)

            val user = call.body()

            runOnUiThread {
                if (user != null) {
                    initUserAddSeenList(user, email)
                }
            }
        }
    }

    private fun gettingUserAddListToWatch(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofitUserByEmail().create(ApiService::class.java)
                .getUser(email)

            val user = call.body()

            runOnUiThread {
                if (user != null) {
                    initUserAddToWatchList(user, email)
                }
            }
        }
    }


    private fun gettingUserRemoveListSeen(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofitUserByEmail().create(ApiService::class.java)
                .getUser(email)

            val user = call.body()

            runOnUiThread {
                if (user != null) {
                    initUserRemoveSeenList(user, email)
                }
            }
        }
    }

    private fun gettingUserRemoveListToWatch(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofitUserByEmail().create(ApiService::class.java)
                .getUser(email)

            val user = call.body()

            runOnUiThread {
                if (user != null) {
                    initUserRemoveToWatchList(user, email)
                }
            }
        }
    }

    private fun initUserAddSeenList(u: User, email: String) {
        inputUser = u
        adapterGetUser = usAdapter(inputUser)
        // PUT METHOD
        if (email != null) {

            for (seen in inputUser.seen) {
                seenList.add(seen)
            }

            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(ApiService::class.java)
                    .getMevie(idmovie.toString() + "?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US")
                val body = call.body()
                if (body != null) {
                    var path = body.backdropPath
                    var titulo = body.title
                    var seen1 = Seen("1", idmovie.toString(), path, titulo)
                    seenList.add(seen1)
                    for (seen in seenList) {
                        println("################ " + seen.toString())
                    }
                    println(inputUser.toString())
                    inputUser.seen = seenList as ArrayList<Seen>
                    println(inputUser.toString())
                    // Instance of RestApi resources
                    val apiService = RestApiService()
                    apiService.putuser(email, inputUser) {
                        if (it?.Id != null) {
                            println(it.toString())
                            Toast.makeText(
                                applicationContext,
                                "User Lists updated",
                                Toast.LENGTH_SHORT
                            ).show()

//                            val intent = Intent(applicationContext, Recomendedfilms::class.java)
//                            startActivity(intent)
//                            finish()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Fail updating Lists",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Email not saved in configuration",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun initUserRemoveSeenList(u: User, email: String) {
        inputUser = u
        adapterGetUser = usAdapter(inputUser)
        // PUT METHOD
        if (email != null) {

//            for (seen in inputUser.seen) {
//                if (seen.movieId == idmovie.toString()) {
//                    seenList.remove(seen)
//                }
//            }

            for (seen in inputUser.seen) {
                if (seen.movieId != idmovie.toString()) {
                    seenListNew.add(seen)
                }
            }
            inputUser.seen = seenListNew as ArrayList<Seen>
//            inputUser.seen = seenList as ArrayList<Seen>
            println(inputUser.toString())
            // Instance of RestApi resources
            val apiService = RestApiService()
            apiService.putuser(email, inputUser) {
                if (it?.Id != null) {
                    println(it.toString())
                    Toast.makeText(
                        applicationContext,
                        "User Lists updated",
                        Toast.LENGTH_SHORT
                    ).show()

//                    val intent = Intent(applicationContext, Recomendedfilms::class.java)
//                    startActivity(intent)
//                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Fail updating Lists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Email not saved in configuration",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initUserAddToWatchList(u: User, email: String) {
        inputUser = u
        adapterGetUser = usAdapter(inputUser)
        // PUT METHOD
        if (email != null) {

            for (watch in inputUser.toWatch) {
                toWatchList.add(watch)
            }

            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(ApiService::class.java)
                    .getMevie(idmovie.toString() + "?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US")
                val body = call.body()
                if (body != null) {
                    var path = body.backdropPath
                    var titulo = body.title
                    var seen1 = Seen("1", idmovie.toString(), path, titulo)
                    toWatchList.add(seen1)
                    for (seen in toWatchList) {
                        println("################ " + seen.toString())
                    }
                    println(inputUser.toString())
                    inputUser.toWatch = toWatchList as ArrayList<Seen>
                    println(inputUser.toString())
                    // Instance of RestApi resources
                    val apiService = RestApiService()
                    apiService.putuser(email, inputUser) {
                        if (it?.Id != null) {
                            println(it.toString())
                            Toast.makeText(
                                applicationContext,
                                "User Lists updated",
                                Toast.LENGTH_SHORT
                            ).show()

//                            val intent = Intent(applicationContext, Recomendedfilms::class.java)
//                            startActivity(intent)
//                            finish()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Fail updating Lists",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Email not saved in configuration",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun initUserRemoveToWatchList(u: User, email: String) {
        inputUser = u
        adapterGetUser = usAdapter(inputUser)
        // PUT METHOD
        if (email != null) {

//            for (seen in inputUser.seen) {
//                if (seen.movieId == idmovie.toString()) {
//                    seenList.remove(seen)
//                }
//            }

            for (watch in inputUser.toWatch) {
                if (watch.movieId != idmovie.toString()) {
                    toWatchListNew.add(watch)
                }
            }
            inputUser.toWatch = toWatchListNew as ArrayList<Seen>
//            inputUser.seen = seenList as ArrayList<Seen>
            println(inputUser.toString())
            // Instance of RestApi resources
            val apiService = RestApiService()
            apiService.putuser(email, inputUser) {
                if (it?.Id != null) {
                    println(it.toString())
                    Toast.makeText(
                        applicationContext,
                        "User Lists updated",
                        Toast.LENGTH_SHORT
                    ).show()

//                    val intent = Intent(applicationContext, Recomendedfilms::class.java)
//                    startActivity(intent)
//                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Fail updating Lists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Email not saved in configuration",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}