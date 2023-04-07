package com.example.pixabayapi.domain.model.api

import com.example.pixabayapi.domain.model.hit.Hit

data class PictureResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<Hit>
)
