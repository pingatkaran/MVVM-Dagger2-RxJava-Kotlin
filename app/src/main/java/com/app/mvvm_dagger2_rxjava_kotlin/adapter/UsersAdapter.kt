package com.app.mvvm_dagger2_rxjava_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.mvvm_dagger2_rxjava_kotlin.R
import com.app.mvvm_dagger2_rxjava_kotlin.databinding.ItemJokeBinding
import com.app.mvvm_dagger2_rxjava_kotlin.databinding.ItemUserBinding
import com.app.mvvm_dagger2_rxjava_kotlin.loadImage
import com.app.mvvm_dagger2_rxjava_kotlin.model.Data
import com.app.mvvm_dagger2_rxjava_kotlin.model.Jokes

class UsersAdapter(private var users: ArrayList<Data>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(DataBindingUtil.bind(view)!!)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        holder.viewbinding.email.setText(users[position].email)
        holder.viewbinding.name.setText(users[position].first_name)
        holder.viewbinding.image.loadImage(users[position].avatar)
    }

    override fun getItemCount(): Int = users.size


    fun update(it: List<Data>) {
        users.clear()
        users.addAll(it)
        notifyDataSetChanged()
    }


    class UserViewHolder(var viewbinding: ItemUserBinding) : RecyclerView.ViewHolder(viewbinding.root)

}