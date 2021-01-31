package com.app.mvvm_dagger2_rxjava_kotlin.network

import com.app.mvvm_dagger2_rxjava_kotlin.model.Data
import com.app.mvvm_dagger2_rxjava_kotlin.model.Jokes
import com.app.mvvm_dagger2_rxjava_kotlin.model.UserModel
import io.reactivex.Single
import retrofit2.http.GET

interface JokesAPI {

    @GET("jokes/ten")
    fun getJokes(): Single<List<Jokes>>


    @GET("users")
    fun getUsers(): Single<UserModel>
}
