package com.rarus7.test_focus.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rarus7.test_focus.domain.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}