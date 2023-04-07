package com.example.pixabayapi.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.pixabayapi.HitApplication
import com.example.pixabayapi.core.Constant.BASE_URL
import com.example.pixabayapi.core.Constant.DATABASE_NAME
import com.example.pixabayapi.data.local.HitDatabase
import com.example.pixabayapi.data.remote.HitApi
import com.example.pixabayapi.data.repository.HitRepositoryImpl
import com.example.pixabayapi.domain.repository.HitRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideHitRepository(db: HitDatabase, api: HitApi) : HitRepository{
        return HitRepositoryImpl(dao = db.hitDao(), api = api)
    }

    @Provides
    @Singleton
    fun provideHitDatabase(application : Application) : HitDatabase{
        return Room.databaseBuilder(context = application, klass = HitDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext app: Context) : HitApplication{
        return app as HitApplication
    }

    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient{
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideHitApi(retrofit: Retrofit) : HitApi{
        return retrofit.create(HitApi::class.java)
    }
}