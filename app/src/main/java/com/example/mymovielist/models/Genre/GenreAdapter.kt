package com.example.mymovielist.models.Genre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.R
import com.example.mymovielist.databinding.ChooseGenresBinding
import com.example.mymovielist.databinding.GenreItemBinding

class GenreAdapter (
    private val genres : List<Genres>
    ) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){

        class GenreViewHolder(view : View) : RecyclerView.ViewHolder(view){
            private val binding = ChooseGenresBinding.bind(view)
            fun binGenre(name : String){

                binding.toggleButton.setBackgroundResource(R.drawable.ic_drama)
                binding.txtViewValidateName.text = name
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return  GenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.choose_genres, parent, false)
        )
    }


    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genero = genres[position].name
        holder.binGenre( genero!!)
    }

    override fun getItemCount(): Int {
        return genres.size
    }
    }
