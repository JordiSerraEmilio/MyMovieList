package com.example.mymovielist.models.Users

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.Movie
import com.example.mymovielist.R
import com.example.mymovielist.databinding.MoviebyidItemBinding

class SeenAdapter(
    private val seen: List<Seen>
    ): RecyclerView.Adapter<SeenAdapter.MovieByIdViewHolder>() {

        class MovieByIdViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val binding = MoviebyidItemBinding.bind(view)
            fun binMovieById(image:String?, name: String?){
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500"+image).into(binding.ivImgMoviebyid)
                binding.tvTitleMoviebyid.text = name
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieByIdViewHolder {
        return MovieByIdViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.moviebyid_item, parent, false )
            )
    }

    override fun onBindViewHolder(holder: MovieByIdViewHolder, position: Int) {
        val result = seen[position]
        holder.binMovieById(result.backdropPath, result.movieTitle)
        holder.itemView.findViewById<ImageView>(R.id.iv_img_moviebyid).setOnClickListener{
            val intent = Intent(it.context, Movie::class.java)
            intent.putExtra("rid", result.movieId!!.toInt())
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return  seen.size
    }


}