package com.example.pixabayapi.domain.model.hit

import android.os.Parcelable
import com.example.pixabayapi.data.local.entity.HitEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hit(
    val id: Int?,
    val pageURL: String?,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val previewWidth: Int?,
    val previewHeight: Int?,
    val webformatURL: String?,
    val webformatWidth: Int?,
    val webformatHeight: Int?,
    val largeImageURL: String?,
    val fullHDURL: String?,
    val imageURL: String?,
    val imageWidth: Int?,
    val imageHeight: Int?,
    val imageSize: Int?,
    val views: Int?,
    val downloads: Int?,
    val likes: Int?,
    val comments: Int?,
    @SerializedName("user_id")
    val userId: Int?,
    val user: String?,
    val userImageURL: String?,
    ) : Parcelable {

    fun toHitEntity() : HitEntity{
        return HitEntity(
            id = id,
            pageURL = pageURL,
            type = type,
            tags = tags,
            previewURL = previewURL,
            previewWidth = previewWidth,
            previewHeight = previewHeight,
            webformatURL = webformatURL,
            webformatWidth = webformatWidth,
            webformatHeight = webformatHeight,
            largeImageURL = largeImageURL,
            fullHDURL = fullHDURL,
            imageURL = imageURL,
            imageWidth = imageWidth,
            imageHeight= imageHeight,
            imageSize = imageSize,
            views = views,
            downloads = downloads,
            likes = likes,
            comments = comments,
            userId = userId,
            user = user,
            userImageURL = userImageURL
        )
    }

}
