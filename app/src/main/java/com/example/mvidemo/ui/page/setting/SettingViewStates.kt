package com.example.mvidemo.ui.page.setting

import com.example.mvidemo.base.IAction
import com.example.mvidemo.base.IEvent
import com.example.mvidemo.base.IState

/**
 * desc: SettingViewStates
 * Author: fengqy
 * Version: V1.0.0
 * Create: 2024/1/11 17:09
 */

sealed class SettingAction : IAction {
    data object OnLoginClicked : SettingAction()
}

sealed class SettingState : IState {
    data object Loading : SettingState()
    data class Error(val error: Throwable) : SettingState()

    val newsList: List<String> = emptyList()
}

sealed class SettingEvent : IEvent {
    data class NavigateToHome(val response: Int) : SettingEvent()
    data class ShowToast(val message: String) : SettingEvent()
    data class RefreshView(val userName: String, val userId: String) : SettingEvent()
}