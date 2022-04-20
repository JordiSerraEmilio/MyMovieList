package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mymovielist.login.PreLoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton1=findViewById<Button>(R.id.bt_register)
        val boton2=findViewById<Button>(R.id.bt_login)
        boton1.setOnClickListener {
            val intento1 = Intent(this, PreLoginActivity::class.java)
            startActivity(intento1)
        }

        boton2.setOnClickListener {
            val intento2 = Intent(this, Recomendedfilms::class.java)
            startActivity(intento2)
        }

    }
}