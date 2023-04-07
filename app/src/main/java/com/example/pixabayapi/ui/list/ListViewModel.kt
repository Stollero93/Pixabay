package com.example.pixabayapi.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixabayapi.domain.model.hit.HitPictureState
import com.example.pixabayapi.domain.model.video.VideoState
import com.example.pixabayapi.domain.usecase.FetchPicturesByStringInputUc
import com.example.pixabayapi.domain.usecase.FetchVideosByStringInputUc
import com.example.pixabayapi.domain.usecase.GetAllLocalPicturesUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val fetchPicturesByStringInputUc: FetchPicturesByStringInputUc,
    private val getAllLocalPicturesUc: GetAllLocalPicturesUc,
    private val fetchVideosByStringInputUc: FetchVideosByStringInputUc
) : ViewModel() {

    private val _hitPictureState = MutableStateFlow<HitPictureState>(HitPictureState.Idle)
    val hitPictureState = _hitPictureState.asStateFlow()

    private val _hitVideoState = MutableStateFlow<VideoState>(VideoState.Idle)
    val hitVideoState = _hitVideoState.asStateFlow()

    fun getPictureHits(isOnline: Boolean, searchQuery: String) {
        viewModelScope.launch {
            if (isOnline) {
                fetchPicturesByStringInputUc.invoke(searchQuery = searchQuery)
            } else {
                getAllLocalPicturesUc.invoke()
            }.collectLatest {
                _hitPictureState.emit(it)
            }
        }
    }

    fun getVideoHits(searchQuery: String) {
        viewModelScope.launch {
            fetchVideosByStringInputUc.invoke(searchQuery = searchQuery).collectLatest {
                _hitVideoState.emit(it)
            }
        }
    }

}