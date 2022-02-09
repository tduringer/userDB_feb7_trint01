package com.example.userdb_feb7_trint01.model

import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
) {
    suspend fun addUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    fun getAllUsers() : Flow<List<UserEntity>> = userDao.getAllUsers()
}