package com.example.pixabayapi.data.remote

import com.example.pixabayapi.BuildConfig
import com.example.pixabayapi.domain.model.api.PictureResponse
import com.example.pixabayapi.domain.model.api.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HitApi {

    @GET("?")
    suspend fun getPictureByQuery(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") searchQuery: String
    ) : Response<PictureResponse>

    @GET("videos?")
    suspend fun getVideosByQuery(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") searchQuery: String
    ) : Response<VideoResponse>
}