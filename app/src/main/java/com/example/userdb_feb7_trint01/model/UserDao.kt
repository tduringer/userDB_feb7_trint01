package com.example.userdb_feb7_trint01.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert
    suspend fun insertAllUsers(users : List<UserEntity>)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM users")
    fun getAllUsers() : Flow<List<UserEntity>>
}