package com.example.userdb_feb7_trint01.viewmodel

import androidx.lifecycle.*
import com.example.userdb_feb7_trint01.model.UserEntity
import com.example.userdb_feb7_trint01.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel(){

    private var _users: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val users : LiveData<List<UserEntity>> get() = _users

    init {
        getAllUsers()
    }

    fun insertUser(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    private fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getAllUsers().collect {
                _users.postValue(it)
            }
        }
    }

    class Factory(
        private val userRepository: UserRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(userRepository) as T
            } else {
                throw RuntimeException("could not create instance of UserViewModel")
            }
        }

    }
}