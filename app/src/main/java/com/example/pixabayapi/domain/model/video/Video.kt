package com.example.pixabayapi.domain.model.video

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val large: VideoFormat?,
    val medium: VideoFormat?,
    val small: VideoFormat?,
    val tiny: VideoFormat?
) : Parcelable