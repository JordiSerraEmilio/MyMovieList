package com.example.mymovielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.example.mymovielist.login.PreLoginActivity
import com.example.mymovielist.models.Recomended.Recomendedfilms
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this,PreLoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}