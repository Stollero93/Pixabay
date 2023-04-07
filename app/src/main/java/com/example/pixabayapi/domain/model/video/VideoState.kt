package com.example.pixabayapi.domain.model.video

sealed class VideoState {

    object Idle: VideoState()
    object Loading: VideoState()

    data class SuccessVideo(
        val videos: List<VideoGeneral>,
    ) : VideoState()

    data class Failure(
        val throwable: Throwable
    ) : VideoState()

}