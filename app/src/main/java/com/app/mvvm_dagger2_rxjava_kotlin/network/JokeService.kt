package com.app.mvvm_dagger2_rxjava_kotlin.network

import com.app.mvvm_dagger2_rxjava_kotlin.di.DaggerApiComponent
import com.app.mvvm_dagger2_rxjava_kotlin.model.Jokes
import com.app.mvvm_dagger2_rxjava_kotlin.model.UserModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class JokeService {

    private val BASE_URL2 = "https://reqres.in/api/"

    @Inject
    lateinit var API: JokesAPI


    private val API2: JokesAPI

    init {
        DaggerApiComponent.create().inject(this)

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