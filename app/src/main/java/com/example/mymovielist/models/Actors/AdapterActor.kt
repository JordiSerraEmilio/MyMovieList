package com.example.mymovielist.models.Actors

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.databinding.ActorItemBinding

class AdapterActor(
    private val cast: List<Cast>
): RecyclerView.Adapter<AdapterActor.CastViewHolder>() {

    class CastViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ActorItemBinding.bind(view)
        fun binActor(
            image: String?,
            name: String?,
            paper: String?
        ){
            if(image.toString().equals("")){
                Glide.with(itemView).load("https://reactnative-examples.com/wp-content/uploads/2022/02/error-image-300x300.png")
                    .into(binding.ivActImg)
            }else{
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500" + image)
                    .into(binding.ivActImg)
            }

            binding.tvActorName.text = name
            binding.tvActorPaper.text = paper
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val result = cast[position]
        holder.binActor(
            result.profilePath,
            result.name,
            result.character
        )
    }

    override fun getItemCount(): Int {
        return cast.size
    }
}