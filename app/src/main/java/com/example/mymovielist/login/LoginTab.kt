package com.example.mymovielist.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mymovielist.EscollirTemes
import com.example.mymovielist.R
import com.example.mymovielist.models.ApiService
import com.example.mymovielist.models.Users.User
import com.example.mymovielist.models.Users.usAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginTab.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginTab : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var allInputValidated: Boolean = false
    private lateinit var adapterGetUser: usAdapter
    private lateinit var inputUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //WORK SPACE //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login_tab2, container, false)

        var btnLogin: Button = view.findViewById<View>(R.id.bttnLogin) as Button

        var inputEmail: EditText = view.findViewById<View>(R.id.editTxtEmail) as EditText
        var labelValidateEmail: TextView =
            view.findViewById<View>(R.id.txtViewValidateEmail) as TextView
        var isValidEmail = false

        var inputPassword: EditText = view.findViewById<View>(R.id.editTxtPassword) as EditText
        var labelValidatePasswordRequire: TextView =
            view.findViewById<View>(R.id.txtViewValidatePasswordRequired) as TextView
        var isValidPassword = false

        inputEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                isValidEmail = validateName(inputEmail, labelValidateEmail)
            }
        }

        inputPassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                isValidPassword = validatePassword(inputPassword, labelValidatePasswordRequire)
            }
        }

        btnLogin.setOnClickListener {
            //DEFINITIVO
            if (validateAllInputs(isValidEmail, isValidPassword)) {
                login(inputEmail.text.toString(), inputPassword.text.toString())
            }
        }
        return view
    }

    private fun validateName(name: EditText, label: TextView): Boolean {
        if (name.length() == 0) {
            label.setText("Required")
            label.visibility = View.VISIBLE
            return false
        } else {
            label.visibility = View.INVISIBLE
            return true
        }
    }

    private fun validatePassword(
        password: EditText,
        labelRequired: TextView,
    ): Boolean {
        if (password.length() == 0) {
            labelRequired.setText("Required")
            labelRequired.visibility = View.VISIBLE
            labelRequired.setTextColor(Color.parseColor("#808080"))
            return false
        } else {
            labelRequired.setText("Required")
            labelRequired.visibility = View.INVISIBLE
            labelRequired.setTextColor(Color.parseColor("#f77814"))
            return true
        }
    }

    private fun validateAllInputs(name: Boolean, password: Boolean): Boolean {
        return name && password
    }

    private fun login(email: String, password: String) {
        Toast.makeText(context, "Please wait...", Toast.LENGTH_SHORT).show()
        gettingUser(email)
    }

    // RETROFIT //

    private fun getRetrofitUserByEmail(): Retrofit {
        return Retrofit.Builder().baseUrl("https://6o5zl5.deta.dev/users/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun gettingUser(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofitUserByEmail().create(ApiService::class.java)
                .getUser(email)

            val user = call.body()

            launch {
                if (user != null) {
                    initUser(user, email)
                }
            }
        }
    }

    private fun initUser(u: User, email: String){
        inputUser = u

        adapterGetUser = usAdapter(inputUser)
        println("######### "+inputUser.toString())
    }
















    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginTab.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginTab().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}