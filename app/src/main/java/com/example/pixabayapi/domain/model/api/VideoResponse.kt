package com.example.pixabayapi.domain.model.api

import com.example.pixabayapi.domain.model.video.VideoGeneral

data class VideoResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<VideoGeneral>
)