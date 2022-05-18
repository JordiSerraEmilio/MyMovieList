package com.example.mymovielist.models.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.databinding.MoviebyidItemBinding
import com.example.mymovielist.models.TopFilms.ResultsTop

class MovieByIdAdapter(
    private val movieById: List< MovieById>
    ): RecyclerView.Adapter<MovieByIdAdapter.MovieByIdViewHolder>() {

        class MovieByIdViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val binding = MoviebyidItemBinding.bind(view)
            fun binMovieById(image:String?, name: String?){
                Glide.with(itemView).load(image).into(binding.ivImgMoviebyid)
                binding.tvTitleMoviebyid.text = name
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByIdViewHolder {
        return MovieByIdViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.moviebyid_item, parent, false )
            )
    }

    override fun onBindViewHolder(holder: MovieByIdViewHolder, position: Int) {
        val result = movieById[position]
        holder.binMovieById(result.backdropPath, result.title)
    }

    override fun getItemCount(): Int {
        return  movieById.size
    }


}