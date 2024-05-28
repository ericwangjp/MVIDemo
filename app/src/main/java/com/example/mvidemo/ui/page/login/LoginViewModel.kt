package com.example.mvidemo.ui.page.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.mvidemo.base.BaseViewModel
import com.example.mvidemo.base.Result
import com.example.mvidemo.constants.LOG_TAG
import com.example.mvidemo.ui.page.login.data.LoginRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

/**
 * desc: LoginViewModel
 * Author: fengqy
 * Version: V1.0.0
 * Create: 2024/1/11 14:29
 */
class LoginViewModel(private val repository: LoginRepository) :
    BaseViewModel<LoginAction, LoginState, LoginEvent>() {
    override fun onAction(action: LoginAction, currentState: LoginState?) {
        when (action) {
            LoginAction.OnLoginClicked -> login(currentState?.userName?:"",currentState?.password?:"")
        }
    }

    private fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        repository.login(username, password).onStart { emitState(LoginState.Loading) }
            .catch { emitState(LoginState.Error(it)) }.onEach {
                when (it) {
                    is Result.Success -> {
                        emitEvent(LoginEvent.RefreshView(it.data.displayName, it.data.userId))
                    }

                    is Result.Error -> {
                        Log.e(LOG_TAG, "login error: ")
                    }
                }

            }.launchIn(viewModelScope)
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }


}