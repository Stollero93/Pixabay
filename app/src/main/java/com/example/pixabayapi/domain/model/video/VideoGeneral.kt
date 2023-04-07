package com.example.pixabayapi.domain.model.video

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoGeneral(
    val id : Int?,
    val pageUrl: String?,
    val type: String?,
    val tags: String?,
    val duration: Int?,
    @SerializedName("picture_id")
    val pictureId: String?,
    val videos: Video?,
    val views: Int?,
    val downloads: Int?,
    val likes: Int?,
    val comments: Int?,
    @SerializedName("user_id")
    val userId: Int?,
    val user: String?,
    val userImageURL: String?
) : Parcelable