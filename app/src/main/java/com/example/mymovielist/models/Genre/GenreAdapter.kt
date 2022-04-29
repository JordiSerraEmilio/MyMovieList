package com.example.mymovielist.models.Genre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.R
import com.example.mymovielist.databinding.ChooseGenresBinding
import com.example.mymovielist.databinding.GenreItemBinding

class GenreAdapter(
    private val genres: List<Genres>
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ChooseGenresBinding.bind(view)
        fun binGenre(name: String) {

            binding.txtViewValidateName.text = name
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
        val genero = genres[position].name
//        val toggle = holder.itemView.findViewById<ToggleButton>(R.id.toggleButton)
//        toggle.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked){
//                Toast.makeText(holder.itemView.context, "ha entrado en el if", Toast.LENGTH_SHORT).show()
//                toggle.setBackgroundResource(R.drawable.ic_drama_focused)
//            }else{
//                toggle.setBackgroundResource(R.drawable.ic_drama)
//            }
//        }
        holder.binGenre(genero!!)
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}
