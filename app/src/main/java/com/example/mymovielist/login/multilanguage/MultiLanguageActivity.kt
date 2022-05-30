package com.example.mymovielist.login.multilanguage

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import com.example.mymovielist.R
import com.example.mymovielist.Users
import com.example.mymovielist.databinding.ActivityMultiLanguageBinding
import com.example.mymovielist.login.PreLoginActivity

class MultiLanguageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMultiLanguageBinding
    private val shared: SharedPreferences = binding.root.context.getSharedPreferences("Language", Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onRadioButtonClicked(binding.root)


        val bu1=findViewById<ImageButton>(R.id.bu_language_save)
        bu1.setOnClickListener {
            val intento1 = Intent(this, PreLoginActivity::class.java)
            startActivity(intento1)
            overridePendingTransition(R.anim.animation0, R.anim.animation0)
            finish();
        }
    }

    fun onRadioButtonClicked(view: View){
        val edit = shared.edit()
        if (view is RadioButton){
            val checked = view.isChecked

            when(view.getId()){
                R.id.rb_english ->
                    if (checked){
                        edit.putString("lang", "english")
                        binding.tvLanguageTitle.text = "LANGUAGE"
                        binding.tvTextLanguage.text = "SELECT YOUR LANGUAGE"
                        binding.rbEnglish.text = "English"
                        binding.rbSpanish.text = "Spanish"
                        binding.rbCatalan.text = "Catalan"
                        binding.buLanguageSave.text = "SAVE"
                    }

                R.id.rb_spanish ->
                    if (checked){
                        edit.putString("lang", "spanish")
                        binding.tvLanguageTitle.text = "LENGUAGE"
                        binding.tvTextLanguage.text = "ELIGE TU IDIOMA"
                        binding.rbEnglish.text = "Ingles"
                        binding.rbSpanish.text = "Español"
                        binding.rbCatalan.text = "Catalan"
                        binding.buLanguageSave.text = "GUARGAR"
                    }

                R.id.rb_catalan ->
                    if (checked){
                        edit.putString("lang", "catalan")
                        binding.tvLanguageTitle.text = "LLENGUATGE"
                        binding.tvTextLanguage.text = "SELECCIONA EL TEU IDIOMA"
                        binding.rbEnglish.text = "Anglès"
                        binding.rbSpanish.text = "Espanyol"
                        binding.rbCatalan.text = "Català"
                        binding.buLanguageSave.text = "GUARGAR"
                    }
            }
        }
        edit.commit()
    }
}