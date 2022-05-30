package com.example.mymovielist.models.Review

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.Movie
import com.example.mymovielist.R
import com.example.mymovielist.User_a
import com.example.mymovielist.databinding.ReviewuserItemBinding
import com.example.mymovielist.models.Recomended.Recomendedfilms
import com.example.mymovielist.models.Users.User

class ReviewUserAdapter(
    private val user: User
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
        val result = user.reviews[position]
        holder.binReviewUser(result.backdropPath, result.movieTitle, result.score, result.comment)
        holder.itemView.findViewById<LinearLayout>(R.id.ll_reviewuser_item).setOnClickListener{
            val intent = Intent(it.context, UpdateCreateReviewActivity::class.java)
            intent.putExtra("oldpath", result.backdropPath)
            intent.putExtra("oldtitle", result.movieTitle)
            intent.putExtra("oldcomment", result.comment)
            intent.putExtra("oldrating", result.score)
            intent.putExtra("oldmovieid", result.movieId)
            it.context.startActivity(intent)
        }
        holder.itemView.findViewById<ImageView>(R.id.iv_img_film_i_ru).setOnClickListener{
//            val intent = Intent(it.context, User_a::class.java)
//            intent.putExtra("uCorreo", user.email)
//            it.context.startActivity(intent)
            val intent = Intent(it.context, Movie::class.java)
            intent.putExtra("rid", result.movieId!!.toInt())
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return user.reviews.size
    }


}

