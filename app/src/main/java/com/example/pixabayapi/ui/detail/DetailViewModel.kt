package com.example.pixabayapi.ui.detail

import androidx.lifecycle.ViewModel
import com.example.pixabayapi.domain.model.hit.Hit
import com.example.pixabayapi.domain.model.hit.HitPictureState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
) : ViewModel() {

    private val _hitPictureState = MutableStateFlow<HitPictureState>(HitPictureState.Idle)
    val hitPictureState = _hitPictureState.asStateFlow()

    fun setHitFromArgument(hit: Hit) {
        _hitPictureState.value = HitPictureState.SuccessLoadingSingleImage(hit)
    }

}