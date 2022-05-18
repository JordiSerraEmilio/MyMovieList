package com.example.mymovielist.models.Review

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.databinding.ReviewfilmItemBinding
import com.example.mymovielist.models.Users.User

class ReviewFilmsAdapter(
    private val users: List<User>
    ): RecyclerView.Adapter<ReviewFilmsAdapter.ReviewFilmUserViewHolder>()  {


        class ReviewFilmUserViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val binding = ReviewfilmItemBinding.bind(view)
            fun binReviewfilm(image:String?, name: String?, point: Float?, coment: String?){
                Glide.with(itemView).load(image).into(binding.ivUserItem)
                binding.tvUsernameItem.text = name
                binding.tvCommnetItem.text = coment
                if (point != null) {
                    binding.rbReviewitem.rating = (point.div(2)).toFloat()
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewFilmUserViewHolder {
        return ReviewFilmUserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.reviewfilm_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewFilmUserViewHolder, position: Int) {
        val result = users[position]
        val rreview = result.reviews[position]
        holder.binReviewfilm(result.image, result.name, rreview.score, rreview.comment )
    }

    override fun getItemCount(): Int {
        return users.size
    }
}