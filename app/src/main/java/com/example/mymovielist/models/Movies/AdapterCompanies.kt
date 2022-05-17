package com.example.mymovielist.models.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymovielist.R
import com.example.mymovielist.databinding.ItemCompaniesBinding

class AdapterCompanies(
    private val productionCompanies: List<ProductionCompanies>
): RecyclerView.Adapter<AdapterCompanies.CompanieViewHolder>() {

    class CompanieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ItemCompaniesBinding.bind(view)
        fun binCompanies(
            image: String?,
            name: String?
        ){
            if(image.toString().equals("")){
                Glide.with(itemView).load("https://reactnative-examples.com/wp-content/uploads/2022/02/error-image-300x300.png")
                    .into(binding.ivItemCompanies)
            }else{
                Glide.with(itemView).load("https://image.tmdb.org/t/p/w500" + image)
                    .into(binding.ivItemCompanies)
            }

            binding.tvItemCompanies.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanieViewHolder {
        return CompanieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_companies, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CompanieViewHolder, position: Int) {
        val result = productionCompanies[position]
        holder.binCompanies(
            result.logoPath,
            result.name
        )
    }

    override fun getItemCount(): Int {
        return productionCompanies.size
    }
}