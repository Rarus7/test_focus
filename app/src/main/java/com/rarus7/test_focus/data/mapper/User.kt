package com.rarus7.test_focus.data.mapper

import com.rarus7.test_focus.data.local.UserEntity
import com.rarus7.test_focus.domain.model.User

fun User.toEntity() =
    UserEntity(
        name = name,
        email = email,
        password = password
    )
