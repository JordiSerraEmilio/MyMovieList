package com.example.mymovielist.models.Users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovielist.R
import com.example.mymovielist.databinding.UserItemBinding
import com.example.mymovielist.models.Genre.GenreAdapter

class UserAdapter(
    private val user: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

        class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val binding = UserItemBinding.bind(view)
            fun binUser(name : String){
                binding.tvUserItemName.text = name
            }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val usuari = user[position]._id
        holder.binUser( usuari!!)
    }

    override fun getItemCount(): Int {
        return user.size
    }
}