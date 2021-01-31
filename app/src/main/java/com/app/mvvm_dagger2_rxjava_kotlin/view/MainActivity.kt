package com.app.mvvm_dagger2_rxjava_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvm_dagger2_rxjava_kotlin.R
import com.app.mvvm_dagger2_rxjava_kotlin.adapter.JokesAdapter
import com.app.mvvm_dagger2_rxjava_kotlin.adapter.UsersAdapter
import com.app.mvvm_dagger2_rxjava_kotlin.databinding.ActivityMainBinding
import com.app.mvvm_dagger2_rxjava_kotlin.viewModel.JokesViewModel

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    lateinit var viewModel: JokesViewModel
    private val jokesAdapter = JokesAdapter(arrayListOf())
    private val usersAdapter = UsersAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)
            .get(JokesViewModel::class.java)
        viewModel.refresh()

        binding!!.jokesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = jokesAdapter

        }

        binding!!.usersList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = usersAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.jokesList.observe(this, Observer {
            it.let {
                jokesAdapter.update(it)
            }
        })
        viewModel.jokesLoadError.observe(this, Observer {
            it.let {
                if (it){
                    binding!!.error.visibility = View.VISIBLE
                }else{
                    binding!!.error.visibility = View.GONE
                }

            }
        })

        viewModel.loadingProgressBar.observe(this, Observer {
            it.let {
                if (it){
                    binding!!.loading.visibility = View.VISIBLE
                }else{
                    binding!!.loading.visibility = View.GONE
                    binding!!.error.visibility = View.GONE
                }

            }
        })

        viewModel.usersList.observe(this, Observer {
            it.let {
                usersAdapter.update(it)
            }
        })

    }
}