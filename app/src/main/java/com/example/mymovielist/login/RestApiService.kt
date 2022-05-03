package com.example.mymovielist.login

import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Users.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {

    fun createuser(userInfo: User, onResult: (User?) -> Unit){
        val retrofit = ApiService.ServiceBuilder.buildService(ApiService::class.java)
        retrofit.postUser(userInfo).enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<User>, response: Response<User>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

//    fun putuser(email: String, userInfo: User, onResult: (User?) -> Unit){
//        val retrofit = ApiService.ServiceBuilderPut.buildService(ApiService::class.java)
//        retrofit.putUser(email, userInfo).enqueue(
//            object : Callback<User> {
//                override fun onFailure(call: Call<User>, t: Throwable) {
//                    onResult(null)
//                }
//                override fun onResponse( call: Call<User>, response: Response<User>) {
//                    val updatedUser = response.body()
//                    onResult(updatedUser)
//                }
//            }
//        )
//    }

//    fun getuser(email: String, onResult: (User?) -> Unit){
//        val retrofit = ApiService.ServiceBuilderGet.buildService(ApiService::class.java)
//        retrofit.getUser(email).enqueue(
//            object : Callback<User> {
//                override fun onFailure(call: Call<User>, t: Throwable) {
//                    onResult(null)
//                }
//                override fun onResponse( call: Call<User>, response: Response<User>) {
//                    val addedUser = response.body()
//                    onResult(addedUser)
//                }
//            }
//        )
//    }
}