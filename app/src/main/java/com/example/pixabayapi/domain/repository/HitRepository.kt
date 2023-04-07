package com.example.pixabayapi.domain.repository

import com.example.pixabayapi.domain.model.hit.Hit
import com.example.pixabayapi.domain.model.hit.HitPictureState
import com.example.pixabayapi.domain.model.video.VideoState
import kotlinx.coroutines.flow.Flow

interface HitRepository {

    suspend fun insertLocalPicture(hit: Hit)
    suspend fun deleteAllLocalEntries()
    suspend fun getAllLocalPictures(): Flow<HitPictureState>
    suspend fun fetchPicturesByStringInput(searchQuery: String): Flow<HitPictureState>
    suspend fun fetchVideosByStringInput(searchQuery: String): Flow<VideoState>

}