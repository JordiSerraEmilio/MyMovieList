package com.example.mymovielist.models.TopFilms

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.Movie
import com.example.mymovielist.R
import com.example.mymovielist.databinding.FilmItemBinding

class TopAdapter(
    private val toplist: List<ResultsTop>
) : RecyclerView.Adapter<TopAdapter.TopViewHolder>() {

    class TopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FilmItemBinding.bind(view)
        fun binFilm(
            image: String?,
            title: String?,
            estrena: String?,
            point: Double?,
            genres: ArrayList<Int>
        ) {
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500" + image)
                .into(binding.ivFilmCv)
            binding.tvFilmTitleCv.text = title
            binding.tvFilmDateCv.text = estrena
            if (point != null) {
                binding.rbFilmCv.rating = (point.div(2)).toFloat()
            }
            for (x in genres) {
                when (x) {
                    28 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Action"
                    12 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Adventure"
                    16 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Animation"
                    35 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Comedy"
                    80 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Crime"
                    99 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Documentary"
                    18 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Drama"
                    10751 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Family"
                    14 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Fantasy"
                    36 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " History"
                    27 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Horror"
                    10402 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Music"
                    9648 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Mystery"
                    10749 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Romance"
                    878 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Science Fiction"
                    10770 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " TV Movie"
                    53 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Thriller"
                    10752 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " War"
                    37 -> binding.tvFilmGenresCv.text =
                        binding.tvFilmGenresCv.text.toString() + " Western"
                    else -> { // Note the block
                        binding.tvFilmGenresCv.text =
                            binding.tvFilmGenresCv.text.toString() + " Undefined genre"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.film_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        val result = toplist[position]
        holder.binFilm(
            result.backdropPath,
            result.title,
            result.releaseDate,
            result.voteAverage,
            result.genreIds
        )
        holder.itemView.findViewById<ImageView>(R.id.iv_film_cv).setOnClickListener{
            val intent = Intent(it.context, Movie::class.java)
            intent.putExtra("rid", result.id)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return toplist.size
    }


}