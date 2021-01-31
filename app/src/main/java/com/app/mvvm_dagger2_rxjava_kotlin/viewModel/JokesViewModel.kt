package com.app.mvvm_dagger2_rxjava_kotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.mvvm_dagger2_rxjava_kotlin.di.DaggerApiComponent
import com.app.mvvm_dagger2_rxjava_kotlin.model.Data
import com.app.mvvm_dagger2_rxjava_kotlin.model.Jokes
import com.app.mvvm_dagger2_rxjava_kotlin.model.UserModel
import com.app.mvvm_dagger2_rxjava_kotlin.network.JokeService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class JokesViewModel : ViewModel() {

    val jokesList = MutableLiveData<List<Jokes>>()
    val usersList = MutableLiveData<List<Data>>()
    val jokesLoadError = MutableLiveData<Boolean>()
    val loadingProgressBar = MutableLiveData<Boolean>()

    @Inject
    lateinit var jokesService : JokeService
    val disposable = CompositeDisposable()


    init {
        DaggerApiComponent.create().inject(this)
    }

    fun refresh() {
        fetchRandomJokes()
        fetchRandomUsers()
    }

    fun fetchRandomJokes() {
        loadingProgressBar.value = true

        disposable.add(
            jokesService.getJokes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Jokes>>() {
                    override fun onSuccess(value: List<Jokes>?) {
                        loadingProgressBar.value = false
                        jokesLoadError.value = false
                        jokesList.value = value
                    }

                    override fun onError(e: Throwable?) {
                        jokesLoadError.value = false
                        loadingProgressBar.value = false
                    }

                })
        )


//        val data = listOf<Jokes>(
//            Jokes(1,"any","any","any"),
//            Jokes(2,"any","any","any"),
//            Jokes(3,"any","any","any"),
//            Jokes(4,"any","any","any"),
//            Jokes(5,"any","any","any"),
//            Jokes(6,"any","any","any"),
//        )
//
//        jokesLoadError.value = false
//        loadingProgressBar.value = false
//        jokesList.value = data
    }

    fun fetchRandomUsers() {
        loadingProgressBar.value = true

        disposable.add(
            jokesService.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserModel>() {
                    override fun onSuccess(value: UserModel?) {
                        loadingProgressBar.value = false
                        jokesLoadError.value = false
                        usersList.value = value!!.data
                    }

                    override fun onError(e: Throwable?) {
                        jokesLoadError.value = false
                        loadingProgressBar.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}