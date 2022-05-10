package com.example.mymovielist.models.Review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.databinding.ReviewuserItemBinding

class ReviewUserAdapter(
    private val reviews: List<Reviews>
    ) : RecyclerView.Adapter<ReviewUserAdapter.ReviewUserViewHolder>() {

        class ReviewUserViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val binding = ReviewuserItemBinding.bind(view)
            fun binReviewUser(imatge:String?, title: String?, point: Float?, coment: String?){
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500"+imatge).into(binding.ivImgFilmIRu)
                binding.tvTitolFilmIRu.text = title
                binding.tvComentFilmIRu.text = coment
                if (point != null) {
                    binding.rbPointsFilmIRu.rating = (point.div(2)).toFloat()
                }

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewUserViewHolder {
        return ReviewUserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.reviewuser_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewUserViewHolder, position: Int) {
        val result = reviews[position]
        holder.binReviewUser(result.backdropPath, result.movieTitle, result.score, result.comment)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }


}

