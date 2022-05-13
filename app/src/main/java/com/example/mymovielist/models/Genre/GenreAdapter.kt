package com.example.mymovielist.models.Genre

import android.content.Context
import android.content.Intent

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.EscollirTemes
import com.example.mymovielist.R
import com.example.mymovielist.databinding.ChooseGenresBinding
import com.example.mymovielist.login.RestApiService
import com.google.gson.Gson
import java.io.Serializable


class GenreAdapter(
    private val genres: List<Genres>,
    private var user_genres: List<Genres>
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ChooseGenresBinding.bind(view)
        fun binGenre(genre: Genres) {
            binding.txtViewValidateName.text = genre.name
            when (binding.txtViewValidateName.text) {
                "Action" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Adventure" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Animation" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Comedy" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Crime" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Documentary" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Drama" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Family" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Fantasy" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "History" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Horror" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Music" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_music_icon)
                "Mystery" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Romance" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Science Fiction" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "TV Movie" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Thriller" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "War" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                "Western" -> binding.toggleButton.setBackgroundResource(R.drawable.custom_drama_icon)
                else -> {
                    print("Not Implemented")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.choose_genres, parent, false)

        )
    }


    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {

        val genero = genres[position]

        val toggle = holder.itemView.findViewById<ToggleButton>(R.id.toggleButton)

        // Cuando se pulsa un toggleBttn, se guarda en la lista el que ha selecionado.
        // Si lo deseleciona, se borra de la lista
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                if(!user_genres.contains(genres[position]))
                    user_genres+=(genres[position])
                    println("##### ADD ##### "+user_genres.toString()) //Comprovación

            }else{
                if(user_genres.contains(genres[position]))
                    user_genres-=(genres[position])
                    println("##### REMOVE ##### "+user_genres.toString()) //Comprovación
            }
//            val intent = Intent(toggle.context, EscollirTemes::class.java)
//            //var arrayList = ArrayList(user_genres)
////            intent.putExtra("user_genres", user_genres)
//            var genreList = arrayOf<Int?>(1, 2, 3, 4)
//            intent.putExtra("genreList", genreList)
        }
        holder.binGenre(genero!!)
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}
