package com.rarus7.test_focus.domain.repository

import com.rarus7.test_focus.domain.model.User

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun checkEmail(email: String): Boolean
}