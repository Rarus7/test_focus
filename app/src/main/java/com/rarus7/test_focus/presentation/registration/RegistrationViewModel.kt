package com.rarus7.test_focus.presentation.registration

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rarus7.test_focus.domain.model.User
import com.rarus7.test_focus.domain.usecase.CheckIfEmailExistsUseCase
import com.rarus7.test_focus.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


private const val TAG = "max--> RegistrationViewModel"
@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase,
    private val checkIfEmailExistsUseCase: CheckIfEmailExistsUseCase
) : ViewModel() {

    private val _toast = MutableSharedFlow<String>()
    val toast = _toast.asSharedFlow()

    fun register(
        name: String,
        email: String,
        password: String,
        confirm: String
    ) {
        viewModelScope.launch {

            when {
                name.length !in 1..35 ->
                    sendError("Имя должно быть от 1 до 35 символов")

                email.length > 50 ||
                        !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                    sendError("Некорректный email")

                password.length !in 5..64 ->
                    sendError("Пароль должен быть от 5 до 64 символов")

                password != confirm ->
                    sendError("Пароли не совпадают")

                checkIfEmailExistsUseCase(email) ->
                    sendError("Этот email уже зарегистрирован")

                else -> save(name, email, password)
            }
        }
    }

    private suspend fun save(name: String, email: String, password: String) {
        saveUserUseCase(User(name, email, password))
        _toast.emit("Сохранено")
    }

    private suspend fun sendError(text: String) {
        _toast.emit(text)
    }
}
