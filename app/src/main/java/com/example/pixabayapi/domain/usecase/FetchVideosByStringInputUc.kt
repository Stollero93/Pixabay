package com.example.pixabayapi.domain.usecase

import com.example.pixabayapi.domain.repository.HitRepository
import javax.inject.Inject

class FetchVideosByStringInputUc @Inject constructor(
    private val repository: HitRepository
) {

    suspend operator fun invoke(searchQuery: String) =
        repository.fetchVideosByStringInput(searchQuery = searchQuery)

}