package com.rarus7.test_focus.data.repository

import com.rarus7.test_focus.data.local.UserDao
import com.rarus7.test_focus.data.mapper.toEntity
import com.rarus7.test_focus.domain.model.User
import com.rarus7.test_focus.domain.repository.UserRepository
import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : UserRepository {

    override suspend fun saveUser(user: User) {
        dao.insert(user.toEntity())
    }
}
