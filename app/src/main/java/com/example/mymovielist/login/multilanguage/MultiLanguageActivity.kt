package com.example.mymovielist.login.multilanguage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import com.example.mymovielist.R
import com.example.mymovielist.Users
import com.example.mymovielist.databinding.ActivityMultiLanguageBinding
import com.example.mymovielist.login.PreLoginActivity

class MultiLanguageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMultiLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val shared: SharedPreferences = applicationContext.getSharedPreferences("Language", Context.MODE_PRIVATE)
        val idioma = shared.getString("lang", "")
        if(idioma == ""){
            binding.rbEnglish.isChecked = true
        }else{
            if(idioma == "english"){
                binding.rbEnglish.isChecked = true
                onRadioButtonClicked( shared)
            }else if(idioma == "spanish"){
                binding.rbSpanish.isChecked = true
                onRadioButtonClicked( shared)
            }else if (idioma == "catalan"){
                binding.rbCatalan.isChecked = true
                onRadioButtonClicked( shared)
            }
        }

        var br = findViewById<RadioButton>(R.id.rb_english)
        br.setOnClickListener {
            println("aaaaaaaaaaaaaaaaaaaaaaa")
            onRadioButtonClicked( shared)
        }

        var br2 = findViewById<RadioButton>(R.id.rb_spanish)
        br2.setOnClickListener {
            println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
            onRadioButtonClicked( shared)
        }

        var br3 = findViewById<RadioButton>(R.id.rb_catalan)
        br3.setOnClickListener {
            println("ccccccccccccccccccccccccccccccc")
            onRadioButtonClicked( shared)
        }



        val bu1=findViewById<Button>(R.id.bu_language_save)
        bu1.setOnClickListener {
            val intento1 = Intent(this, PreLoginActivity::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
    }

    fun onRadioButtonClicked( shared : SharedPreferences){
        val edit = shared.edit()
        println("primer paso")
        if (binding.rbEnglish.isChecked){
            println("segundo paso")
            println(binding.rbEnglish.text.toString() + "   " + binding.rbEnglish.isChecked)
            edit.putString("lang", "english")
            binding.tvLanguageTitle.text = "LANGUAGE"
            binding.tvTextLanguage.text = "SELECT YOUR LANGUAGE"
            binding.rbEnglish.text = "English"
            binding.rbSpanish.text = "Spanish"
            binding.rbCatalan.text = "Catalan"
            binding.buLanguageSave.text = "SAVE"
        }else if(binding.rbSpanish.isChecked){
            println("segundo paso")
            println(binding.rbSpanish.text.toString() + "   " + binding.rbSpanish.isChecked)
            edit.putString("lang", "spanish")
            binding.tvLanguageTitle.text = "LENGUAGE"
            binding.tvTextLanguage.text = "ELIGE TU IDIOMA"
            binding.rbEnglish.text = "Ingles"
            binding.rbSpanish.text = "Español"
            binding.rbCatalan.text = "Catalan"
            binding.buLanguageSave.text = "GUARDAR"
        }else if(binding.rbCatalan.isChecked){
            println("segundo paso")
            println(binding.rbCatalan.text.toString() + "   " + binding.rbCatalan.isChecked)
            edit.putString("lang", "catalan")
            binding.tvLanguageTitle.text = "LLENGUATGE"
            binding.tvTextLanguage.text = "SELECCIONA EL TEU IDIOMA"
            binding.rbEnglish.text = "Anglès"
            binding.rbSpanish.text = "Espanyol"
            binding.rbCatalan.text = "Català"
            binding.buLanguageSave.text = "GUARDAR"
        }
        edit.commit()
    }
}