package com.example.mvidemo.ui.page.login

import com.example.mvidemo.base.IAction
import com.example.mvidemo.base.IEvent
import com.example.mvidemo.base.IState

/**
 * desc: LoginViewStates
 * Author: fengqy
 * Version: V1.0.0
 * Create: 2024/1/11 17:09
 */

sealed class LoginAction : IAction {
    data object OnLoginClicked : LoginAction()
}

sealed class LoginState : IState {
    data object Loading : LoginState()
    data class Error(val error: Throwable) : LoginState()

//    需考虑这些数据是否需要在ViewModel中存储，以及合理性
    val newsList: List<String> = emptyList()
    val userName:String=""
    val password:String=""
}

sealed class LoginEvent : IEvent {
    data class NavigateToHome(val response: Int) : LoginEvent()
    data class ShowToast(val message: String) : LoginEvent()
    data class RefreshView(val userName: String, val userId: String) : LoginEvent()

    data object GetNewsList : LoginEvent()
}