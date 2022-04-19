package com.example.mymovielist.models.TopFilms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.databinding.FilmItemBinding

class TopAdapter (
    private val toplist : List<ResultsTop>
) : RecyclerView.Adapter<TopAdapter.TopViewHolder>(){

    class TopViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = FilmItemBinding.bind(view)
        fun binFilm(image:String?, title: String?, estrena: String?, point: Double?, genres: ArrayList<Int>){
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500"+image).into(binding.ivFilmCv)
            binding.tvFilmTitleCv.text = title
            binding.tvFilmDateCv.text = estrena
            if (point != null) {
                binding.rbFilmCv.rating = (point.div(2)).toFloat()
            }
            //binding.tvFilmGenresCv.text = genres.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
       val result = toplist[position]
       holder.binFilm(result.backdropPath, result.title, result.releaseDate, result.voteAverage, result.genreIds)
    }

    override fun getItemCount(): Int {
        return toplist.size
    }


}