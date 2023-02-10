package com.test.androidmvvmapp.di

import android.app.Application
import androidx.room.Room
import com.test.androidmvvmapp.data.local.LocalDataSource
import com.test.androidmvvmapp.data.local.PostDatabase
import com.test.androidmvvmapp.data.remote.PostApi
import com.test.androidmvvmapp.data.repository.PostRepositoryImpl
import com.test.androidmvvmapp.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostApi() : PostApi {
        return Retrofit.Builder()
            .baseUrl(PostApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providesPostDatabase(application: Application) : PostDatabase {
        return Room.databaseBuilder(
            application,
            PostDatabase::class.java,
            PostDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePostRepository(postApi: PostApi, localDataSource: LocalDataSource) : PostRepository{
        return PostRepositoryImpl(postApi, localDataSource)
    }
}