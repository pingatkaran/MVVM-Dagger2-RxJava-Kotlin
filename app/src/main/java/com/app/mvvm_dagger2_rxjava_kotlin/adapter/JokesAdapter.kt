package com.app.mvvm_dagger2_rxjava_kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvm_dagger2_rxjava_kotlin.R
import com.app.mvvm_dagger2_rxjava_kotlin.databinding.ItemJokeBinding
import com.app.mvvm_dagger2_rxjava_kotlin.model.Jokes

class JokesAdapter(private var jokes: ArrayList<Jokes>) :
    RecyclerView.Adapter<JokesAdapter.JokesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_joke, parent, false)
        return JokesViewHolder(DataBindingUtil.bind(view)!!)
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {

        holder.viewbinding.question.setText(jokes[position].setup)
        holder.viewbinding.answer.setText(jokes[position].punchline)

    }

    override fun getItemCount(): Int = jokes.size


    fun update(it: List<Jokes>) {
        jokes.clear()
        jokes.addAll(it)
        notifyDataSetChanged()
    }


    class JokesViewHolder(var viewbinding: ItemJokeBinding) : RecyclerView.ViewHolder(viewbinding.root)

}