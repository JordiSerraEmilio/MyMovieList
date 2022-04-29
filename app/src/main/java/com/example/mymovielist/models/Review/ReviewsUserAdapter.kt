package com.example.mymovielist.models.Review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.Reviews_a
import com.example.mymovielist.databinding.ReviewsuserItemBinding

class ReviewsUserAdapter(
    private val reviews: List<Reviews>
)
    : RecyclerView.Adapter<ReviewsUserAdapter.ReviewsUserViewHolder>() {

    class ReviewsUserViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ReviewsuserItemBinding.bind(view)

        fun binReviewsUser(image:String?,titulo : String?, commentario : String?, puntos : Float? ){
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w500"+image).into(binding.ivReviewItem)
            binding.titolReviewItem.text = titulo
            binding.descripReviewItem.text = commentario
            if (puntos != null) {
                binding.rbReviewItem.rating = (puntos.div(2))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsUserViewHolder {
        return  ReviewsUserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.reviewsuser_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewsUserViewHolder, position: Int) {
        val result = reviews[position]
        holder.binReviewsUser(result.backdropPath, result.movieTitle,
            result.comment, result.score)
    }

    override fun getItemCount(): Int {
        return  reviews.size
    }

}