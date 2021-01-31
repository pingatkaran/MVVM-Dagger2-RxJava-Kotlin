package com.app.mvvm_dagger2_rxjava_kotlin.di

import com.app.mvvm_dagger2_rxjava_kotlin.network.JokeService
import com.app.mvvm_dagger2_rxjava_kotlin.viewModel.JokesViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: JokeService)
    fun inject(viewModel: JokesViewModel)
}