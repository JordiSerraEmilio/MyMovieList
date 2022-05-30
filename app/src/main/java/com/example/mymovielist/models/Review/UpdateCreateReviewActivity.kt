package com.example.mymovielist.models.Review

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.mymovielist.R
import com.example.mymovielist.Reviews_a
import com.example.mymovielist.login.RestApiService
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.usAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpdateCreateReviewActivity : AppCompatActivity() {

    private lateinit var adapterGetUser: usAdapter
    private lateinit var inputUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_create_review)

        val bttnGoBack = findViewById<ImageButton>(R.id.imgBttnGoBack)
        bttnGoBack.setOnClickListener {
            super.onBackPressed()
        }

        var editTxt = findViewById<EditText>(R.id.t_upd_review)
        editTxt.setText(intent.getStringExtra("oldcomment"))

        var ratingBar = findViewById<RatingBar>(R.id.rb_upd_review)
        ratingBar.rating = intent.getFloatExtra("oldrating", 0.0f)/2

        val bttnUpdate = findViewById<Button>(R.id.bttnUpdateReview)
        bttnUpdate.setOnClickListener {
            UpdateUserReview()
        }
    }


    private fun UpdateUserReview() {
        val shared: SharedPreferences =
            applicationContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
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

    private fun initUser(u: User, email: String) {
        inputUser = u
        adapterGetUser = usAdapter(inputUser)
        // PUT METHOD
        if (email != null) {

            var mutable = mutableListOf<Reviews>()
            for (review in inputUser.reviews) {
                if (review.movieId != intent.getStringExtra("oldmovieid")){
                    mutable.add(review)
                }
            }
            val movieId = intent.getStringExtra("oldmovieid").toString()
            println("######################## " + movieId)
            var path = ""
            var title = ""
            CoroutineScope(Dispatchers.IO).launch {
                val call = getRetrofit().create(ApiService::class.java)
                    .getMevie(movieId + "?api_key=902a2e71fa0c8a74cbe2fc39a4560b99&language=en-US")
                val body = call.body()
                if (body != null) {
                    runOnUiThread {
                        path = body.backdropPath.toString()
                        title = body.title.toString()

                        val oldReview = Reviews(
                            "1",
                            movieId,
                            intent.getStringExtra("oldpath"),
                            intent.getStringExtra("oldtitle"),
                            intent.getStringExtra("oldcomment"),
                            intent.getFloatExtra("oldrating", 0.0f)/2
                        )

                        val newReview = Reviews(
                            "1",
                            movieId,
                            intent.getStringExtra("oldpath"),
                            intent.getStringExtra("oldtitle"),
                            findViewById<EditText>(R.id.t_upd_review).text.toString(),
                            findViewById<RatingBar>(R.id.rb_upd_review).rating * 2
                        )

//                        mutable.remove(oldReview)
                        mutable.add(newReview)
                        inputUser.reviews = mutable as ArrayList<Reviews>


                        // Instance of RestApi resources
                        val apiService = RestApiService()
                        apiService.putuser(email, inputUser) {
                            if (it?.Id != null) {
                                println(it.toString())
                                Toast.makeText(
                                    applicationContext,
                                    "User Review updated",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent = Intent(applicationContext, Reviews_a::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Fail updating Review",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}