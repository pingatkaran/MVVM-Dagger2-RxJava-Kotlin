package com.app.mvvm_dagger2_rxjava_kotlin.di

import com.app.mvvm_dagger2_rxjava_kotlin.network.JokeService
import com.app.mvvm_dagger2_rxjava_kotlin.network.JokesAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://official-joke-api.appspot.com/"

    @Provides
    fun provideJokesAPI(): JokesAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(JokesAPI::class.java)
    }

    @Provides
    fun provideJokeService(): JokeService {
        return JokeService()
    }
}