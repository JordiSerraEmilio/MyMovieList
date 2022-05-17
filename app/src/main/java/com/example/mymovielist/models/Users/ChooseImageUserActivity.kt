package com.example.mymovielist.models.Users

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import com.example.mymovielist.R
import com.example.mymovielist.login.RestApiService
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChooseImageUserActivity : AppCompatActivity() {

    private var imageUrl = ""
    private lateinit var adapterGetUser: usAdapter
    private lateinit var inputUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_image_user)

        val bttnChange = findViewById<Button>(R.id.bttnChangeImage)
        val group = findViewById<MaterialButtonToggleGroup>(R.id.toggle_button_group)

        group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked){
                when(checkedId){
                    1 -> imageUrl = "https://icones.pro/wp-content/uploads/2021/02/icone-utilisateur-orange.png"
                    2 -> imageUrl = "https://icon-library.com/images/movies-icon-png/movies-icon-png-10.jpg"
                    3 -> imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/1200px-User_icon_2.svg.png"
                }
            }
        }

        bttnChange.setOnClickListener {
            UpdateUserImage()
        }
    }

    // region CAMBIAR IMAGEN

    private fun UpdateUserImage(){
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
            inputUser.image = imageUrl

            // Instance of RestApi resources
            val apiService = RestApiService()
            apiService.putuser(email, inputUser){
                if (it?.Id != null){
                    println(it.toString())
                    Toast.makeText(this.applicationContext, "User image updated", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this.applicationContext, Recomendedfilms::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this.applicationContext, "Fail updating image", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(this.applicationContext, "Email not saved in configuration", Toast.LENGTH_SHORT).show()
        }
    }

    // endregion
}