package com.example.mymovielist.models.Users

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.User_a
import com.example.mymovielist.databinding.UserItemBinding
import com.example.mymovielist.models.Genre.GenreAdapter

class UserAdapter(
    private val user: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

        class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val binding = UserItemBinding.bind(view)

            fun binUser(image:String?,name : String?){
                var i : String?
                if(image.toString().equals("")){
                    i = "https://icones.pro/wp-content/uploads/2021/02/icone-utilisateur-orange.png"
                }else{
                    i = image
                }

                Glide.with(itemView).load(i).into(binding.ivUserItemImatge)
                binding.tvUserItemName.text = name
            }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val usuari = user[position]
        holder.binUser(usuari.image, usuari.name)
        holder.itemView.findViewById<LinearLayout>(R.id.ll_user).setOnClickListener{
            val intent = Intent(it.context, User_a::class.java)
            intent.putExtra("uCorreo", usuari.email)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return user.size
    }
}