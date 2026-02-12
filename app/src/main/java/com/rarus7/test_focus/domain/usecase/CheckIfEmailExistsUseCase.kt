package com.rarus7.test_focus.domain.usecase

import com.rarus7.test_focus.data.local.UserDao
import jakarta.inject.Inject

class CheckIfEmailExistsUseCase @Inject constructor(
    private val userDao: UserDao // или другая реализация доступа к данным
) {

    suspend operator fun invoke(email: String): Boolean {
        return userDao.getUserByEmail(email) != null
    }
}
