package com.app.mvvm_dagger2_rxjava_kotlin.network

import com.app.mvvm_dagger2_rxjava_kotlin.model.Data
import com.app.mvvm_dagger2_rxjava_kotlin.model.Jokes
import com.app.mvvm_dagger2_rxjava_kotlin.model.UserModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class JokeService {

    private val BASE_URL = "https://official-joke-api.appspot.com/"
    private val BASE_URL2 = "https://reqres.in/api/"
    private val API: JokesAPI
    private val API2: JokesAPI

    init {
        API = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(JokesAPI::class.java)

        API2 = Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(JokesAPI::class.java)
    }

    fun getJokes(): Single<List<Jokes>> {
        return API.getJokes()
    }
    fun getUsers(): Single<UserModel> {
        return API2.getUsers()
    }
}