package com.example.pixabayapi.data.repository

import com.example.pixabayapi.data.local.HitDao
import com.example.pixabayapi.data.remote.HitApi
import com.example.pixabayapi.domain.model.hit.Hit
import com.example.pixabayapi.domain.model.hit.HitPictureState
import com.example.pixabayapi.domain.model.video.VideoState
import com.example.pixabayapi.domain.repository.HitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class HitRepositoryImpl(
    private val dao: HitDao,
    private val api: HitApi
) : HitRepository {

    override suspend fun insertLocalPicture(hit: Hit) {
        dao.insert(entity = hit.toHitEntity())
    }

    override suspend fun deleteAllLocalEntries() {
        dao.deleteAllLocal()
    }

    override suspend fun getAllLocalPictures(): Flow<HitPictureState> = flow {
        emit(HitPictureState.Loading)
        val success = HitPictureState.SuccessLoadingImages(dao.getAllLocal().map {
            it.toHit()
        })
        emit(success)
    }. catch {
        emit(HitPictureState.Failure(throwable = it))
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchPicturesByStringInput(searchQuery: String): Flow<HitPictureState> = flow {
        emit(HitPictureState.Loading)
        val response = api.getPictureByQuery(searchQuery = searchQuery)
        val responseBody = response.body()
        val hits = responseBody?.hits
        if(hits != null && response.isSuccessful){
            withContext(Dispatchers.IO){
                deleteAllLocalEntries()
                hits.forEach {
                    insertLocalPicture(hit = it)
                }
            }
            emit(HitPictureState.SuccessLoadingImages(hits = hits))
        }
    }.catch {
        emit(HitPictureState.Failure(throwable = it))
    }.flowOn(Dispatchers.Default)

    override suspend fun fetchVideosByStringInput(searchQuery: String): Flow<VideoState> = flow {
        emit(VideoState.Loading)
        val videoResponse = api.getVideosByQuery(searchQuery = searchQuery)
        val videoResponseBody = videoResponse.body()
        val videos = videoResponseBody?.hits
        if (videos != null && videoResponse.isSuccessful){
            emit(VideoState.SuccessVideo(videos = videos))
        }
    }.catch {
        emit(VideoState.Failure(throwable = it))
    }

}
