package com.example.userdb_feb7_trint01.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userdb_feb7_trint01.databinding.ItemUserBinding
import com.example.userdb_feb7_trint01.model.UserEntity

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList = mutableListOf<UserEntity>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun submitUsers(users: List<UserEntity>) {
        userList.clear()
        userList.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(private val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            with(binding) {
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
            }
        }
    }
}