package com.example.pixabayapi.domain.model.hit

sealed class HitPictureState {

    object Idle : HitPictureState()
    object Loading : HitPictureState()

    data class SuccessLoadingImages(
        val hits : List<Hit>
    ) : HitPictureState()

    data class SuccessLoadingSingleImage(
        val hit: Hit
    ) : HitPictureState()

    data class Failure(
        val throwable: Throwable
    ) : HitPictureState()

}