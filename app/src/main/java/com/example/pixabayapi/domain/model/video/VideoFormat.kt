package com.example.pixabayapi.domain.model.video

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoFormat(
    val url: String?,
    val width: Int?,
    val height: Int?,
    val size: Long?
) : Parcelable