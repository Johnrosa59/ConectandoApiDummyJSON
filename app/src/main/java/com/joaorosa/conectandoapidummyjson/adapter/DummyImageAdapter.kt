package com.joaorosa.conectandoapidummyjson.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joaorosa.conectandoapidummyjson.databinding.DummyRecyclerViewItemBinding
import com.joaorosa.conectandoapidummyjson.model.DummyImages
import com.squareup.picasso.Picasso

class DummyImageAdapter : RecyclerView.Adapter<DummyImageAdapter.DummyImageViewHolder>() {

    private var listDummyImages = mutableListOf<DummyImages>()

    fun addList(list: List<DummyImages>){
        this.listDummyImages.addAll(list)
        notifyDataSetChanged()
    }
    class DummyImageViewHolder(val binding: DummyRecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(dummyImages: DummyImages){
            val dummyImage = dummyImages.thumbnail

            Picasso.get()
                .load(dummyImage)
                .resize(200, 200)
                .centerCrop()
                .into(binding.imgDummy)

            binding.titleDummy.text = dummyImages.title

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DummyImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DummyRecyclerViewItemBinding.inflate(layoutInflater, parent, false)

        return DummyImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DummyImageViewHolder, position: Int) {
        val dummyThings = listDummyImages[position]
        holder.bind(dummyThings)
    }

    override fun getItemCount(): Int {
        return listDummyImages.size

    }



}