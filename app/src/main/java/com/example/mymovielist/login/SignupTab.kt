package com.example.mymovielist.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mymovielist.R
import org.w3c.dom.Text


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignupTab.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignupTab : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var allInputValidated: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_signup_tab2, container, false)

        //WORK SPACE //

        var btnSignup: Button = view.findViewById<View>(R.id.bttnLogin) as Button

        var inputName: EditText = view.findViewById<View>(R.id.editTxtName) as EditText
        var labelValidateName: TextView =
            view.findViewById<View>(R.id.txtViewValidateName) as TextView
        var isValidName = false

        var inputEmail: EditText = view.findViewById<View>(R.id.editTxtEmail) as EditText
        var labelValidateEmail: TextView =
            view.findViewById<View>(R.id.txtViewValidateEmail) as TextView
        var isValidEmail = false

        var inputPassword: EditText = view.findViewById<View>(R.id.editTxtPassword) as EditText
        var labelValidatePasswordRequire: TextView =
            view.findViewById<View>(R.id.txtViewValidatePasswordRequired) as TextView
        var labelValidatePasswordSpecial: TextView =
            view.findViewById<View>(R.id.txtViewValidatePasswordSpecial) as TextView
        var labelValidatePasswordLength: TextView =
            view.findViewById<View>(R.id.txtViewValidatePasswordLength) as TextView
        var isValidPassword = false
        var len = 0

        inputName.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                isValidName = validateName(inputName, labelValidateName)
            }
        }

        inputEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                isValidEmail = validateEmail(inputEmail, labelValidateEmail)
            }
        }

        inputPassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                isValidPassword = validatePassword(
                    inputPassword,
                    labelValidatePasswordRequire,
                    labelValidatePasswordSpecial,
                    labelValidatePasswordLength,
                    len
                )
            }
        }

        inputPassword.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            len = inputPassword.text.length
            labelValidatePasswordLength.setText("$len/8")
            isValidPassword = validatePassword(
                inputPassword,
                labelValidatePasswordRequire,
                labelValidatePasswordSpecial,
                labelValidatePasswordLength,
                len
            )
            true
        })

        btnSignup.setOnClickListener {
//            if (validateInputs(inputName, inputEmail, inputPassword)) {
//                signup()
//            }
            if (validateAllInputs(isValidName, isValidEmail, isValidPassword)) {
                signup()
            }
        }

        // ......... //
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignupTab.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignupTab().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    // FUNCTIONS SPACE //

    private fun validateInputs(name: EditText, email: EditText, password: EditText): Boolean {

        if (name.length() == 0) {
            name.setError("Username is required")
            return false
        }

        if (email.length() == 0) {
            email.setError("Email is required")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.setError("Email format not valid")
            return false
        }

        if (password.length() == 0) {
            password.setError("Password is required")
            return false
        } else if (password.length() < 8) {
            password.setError("Password must be at least 8 characters")
            return false
        } else if (!password.text.matches(".*[^A-Za-z0-9].*".toRegex())) {
            password.setError("Password must have at least 1 special character")
            return false
        }
        return true
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

    private fun validateEmail(email: EditText, label: TextView): Boolean {
        if (email.length() == 0) {
            label.setText("Required")
            label.visibility = View.VISIBLE
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            label.setText("Email format not valid")
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
        labelSpecial: TextView,
        labelLength: TextView,
        length: Int
    ): Boolean {
        if (password.length() == 0) {
            labelRequired.setText("Required")
            labelRequired.visibility = View.VISIBLE
            labelRequired.setTextColor(Color.parseColor("#808080"))
            return false
        } else {
            labelRequired.setText("Required")
            labelRequired.visibility = View.VISIBLE
            labelRequired.setTextColor(Color.parseColor("#f77814"))
        }

        if (length < 8) {
            labelLength.visibility = View.VISIBLE
            labelLength.setTextColor(Color.parseColor("#808080"))
            return false
        } else {
            labelLength.visibility = View.VISIBLE
            labelLength.setTextColor(Color.parseColor("#f77814"))
        }

        if (!password.text.matches(".*[^A-Za-z0-9].*".toRegex())) {
            labelSpecial.visibility = View.VISIBLE
            labelSpecial.setTextColor(Color.parseColor("#FF018786"))
            return false
        } else {
            labelLength.visibility = View.VISIBLE
            labelLength.setTextColor(Color.parseColor("#f77814"))
        }

        return true
    }

    private fun validateAllInputs(name: Boolean, email: Boolean, password: Boolean): Boolean {
        return name && email && password
    }

    private fun signup() {
        Toast.makeText(context, "Making SignUp...", Toast.LENGTH_SHORT).show()
    }


    // .............. //
}