package com.example.pixabayapi.ui.list

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pixabayapi.R
import com.example.pixabayapi.core.setPictureDataFromHit
import com.example.pixabayapi.databinding.ViewListHitItemVideoBinding
import com.example.pixabayapi.domain.model.video.VideoGeneral
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class VideoAdapter : ListAdapter <VideoGeneral, VideoAdapter.VideoViewHolder>(object: DiffUtil.ItemCallback<VideoGeneral>(){

    override fun areItemsTheSame(oldItem: VideoGeneral, newItem: VideoGeneral): Boolean {
       return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: VideoGeneral, newItem: VideoGeneral): Boolean {
       return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(ViewListHitItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val ctx = holder.itemView.context
        val currentVideo = currentList[position]
        holder.binding.exoplayerListHitVideo.player = ExoPlayer.Builder(ctx).build().apply {
            setMediaItem(MediaItem.fromUri(Uri.parse(currentVideo.videos?.tiny?.url)))
            prepare()
        }
        holder.binding.rowViews.tvRowLabel.text = ctx.getString(R.string.detail_label_views)
        holder.binding.rowViews.tvRowValue.text = currentVideo.views.toString()
        holder.binding.rowDownloads.tvRowLabel.text = ctx.getString(R.string.detail_label_downloads)
        holder.binding.rowDownloads.tvRowValue.text = currentVideo.downloads.toString()
        holder.binding.rowLikes.tvRowLabel.text = ctx.getString(R.string.detail_label_likes)
        holder.binding.rowLikes.tvRowValue.text = currentVideo.likes.toString()
        holder.binding.rowComments.tvRowLabel.text = ctx.getString(R.string.detail_label_comments)
        holder.binding.rowComments.tvRowValue.text = currentVideo.comments.toString()
        holder.binding.rowUserId.tvRowLabel.text = ctx.getString(R.string.detail_label_user_id)
        holder.binding.rowUserId.tvRowValue.text = currentVideo.userId.toString()
        holder.binding.rowUser.tvRowLabel.text = ctx.getString(R.string.detail_label_user)
        holder.binding.rowUser.tvRowValue.text = currentVideo.user
        holder.binding.tvLabelImageUrl.text = ctx.getString(R.string.detail_label_user_image_url)

        holder.binding.ivDetailUserPicture.setPictureDataFromHit(context = ctx, imageUrl = currentVideo.userImageURL.toString())
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    inner class VideoViewHolder(val binding: ViewListHitItemVideoBinding) : ViewHolder(binding.root)
}