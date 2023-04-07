package com.example.pixabayapi.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pixabayapi.databinding.ViewListHitItemPictureBinding
import com.example.pixabayapi.domain.model.hit.Hit

class PictureAdapter(
    private val onHitClicked : (hit: Hit) -> Unit
) : ListAdapter<Hit, PictureAdapter.HitViewHolder>(object: DiffUtil.ItemCallback<Hit>(){

    override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
      return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
       return oldItem == newItem
    }
}){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder {
        return HitViewHolder(ViewListHitItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        val currentHit = currentList[position]
        holder.binding.tvHitTags.text = currentHit.tags
        holder.binding.tvHitUser.text = currentHit.user
        Glide
            .with(holder.itemView.context)
            .load(currentHit.previewURL)
            .centerCrop()
            .into(holder.binding.ivHitPicture)
        holder.binding.root.setOnClickListener { onHitClicked(currentHit) }
    }

    override fun getItemCount() = currentList.size

    inner class HitViewHolder (val binding : ViewListHitItemPictureBinding) : ViewHolder(binding.root)

}