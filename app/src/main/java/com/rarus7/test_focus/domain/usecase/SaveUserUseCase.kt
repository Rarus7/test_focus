package com.rarus7.test_focus.domain.usecase

import com.rarus7.test_focus.domain.model.User
import com.rarus7.test_focus.domain.repository.UserRepository
import jakarta.inject.Inject


class SaveUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.saveUser(user)
    }
}