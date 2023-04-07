package com.example.pixabayapi.domain.usecase

import com.example.pixabayapi.domain.repository.HitRepository
import javax.inject.Inject

class GetAllLocalPicturesUc @Inject constructor(
    private val repository: HitRepository
) {

    suspend operator fun invoke() = repository.getAllLocalPictures()

}