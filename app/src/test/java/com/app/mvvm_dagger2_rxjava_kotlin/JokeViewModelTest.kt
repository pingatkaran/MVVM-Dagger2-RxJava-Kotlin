package com.app.mvvm_dagger2_rxjava_kotlin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.mvvm_dagger2_rxjava_kotlin.model.Jokes
import com.app.mvvm_dagger2_rxjava_kotlin.network.JokeService
import com.app.mvvm_dagger2_rxjava_kotlin.viewModel.JokesViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class JokeViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var JokeService: JokeService

    @InjectMocks
    var jokeViewModel = JokesViewModel()

    private var testJokesList: Single<List<Jokes>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getJokesSuccess() {
        val jokes = Jokes(1212, "3434", "#4343", "34343")
        val jokesList = arrayListOf(jokes)

        testJokesList = Single.just(jokesList)
        Mockito.`when`(JokeService.getJokes()).thenReturn(testJokesList)

        jokeViewModel.refresh()

        Assert.assertEquals(1, jokeViewModel.jokesList.value!!.size)
        Assert.assertEquals(false, jokeViewModel.jokesLoadError.value)
        Assert.assertEquals(false, jokeViewModel.loadingProgressBar.value)
    }

    @Test
    fun getJokesFailure() {

        testJokesList = Single.error(Throwable())
        Mockito.`when`(JokeService.getJokes()).thenReturn(testJokesList)

        jokeViewModel.refresh()

        Assert.assertEquals(true, jokeViewModel.jokesLoadError.value)
        Assert.assertEquals(false, jokeViewModel.loadingProgressBar.value)
    }

    @Before
    fun setUpRxSchedulers() {
        val immidiate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler ->
            immidiate
        }

        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler ->
            immidiate
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler ->
            immidiate
        }

        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler ->
            immidiate

        }

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler ->
            immidiate
        }


    }
}