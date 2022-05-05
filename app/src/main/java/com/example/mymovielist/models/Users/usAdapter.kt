package com.example.mymovielist.models.Users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.databinding.UserItemBinding

class usAdapter(
    private val user: User)
    : RecyclerView.Adapter<usAdapter.usViewHolder>() {
    class usViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = UserItemBinding.bind(view)

        fun binUser(image:String?,name : String?){
            Glide.with(itemView).load(image).into(binding.ivUserItemImatge)
            binding.tvUserItemName.text = name
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): usViewHolder {
        return usViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: usViewHolder, position: Int) {
        val usuari = user
        holder.binUser(usuari.image, usuari.name)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}