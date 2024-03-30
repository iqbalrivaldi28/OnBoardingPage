package com.example.onboardingpage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingpage.databinding.SliderItemBinding

class SliderAdapter(private val images: IntArray, private val context: Context): RecyclerView.Adapter<SliderAdapter.SliderAdapterViewHolder>()
{
    class SliderAdapterViewHolder(var binding: SliderItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapterViewHolder {
        val binding = SliderItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return SliderAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapterViewHolder, position: Int) {
        val imagesRes = images[position]
        holder.binding.imageView.setImageResource(imagesRes)
    }

    override fun getItemCount() = images.size

}