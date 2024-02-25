package com.example.mvidemo.ui.page.setting

import com.example.mvidemo.base.BaseViewModel
import com.example.mvidemo.ui.page.setting.data.SettingRepository

/**
 * desc: SettingViewModel
 * Author: fengqy
 * Version: V1.0.0
 * Create: 2024/1/11 14:29
 */
class SettingViewModel(private val repository: SettingRepository) :
    BaseViewModel<SettingAction, SettingState, SettingEvent>() {

    override fun onAction(action: SettingAction, currentState: SettingState?) {
        when (action) {
            SettingAction.OnLoginClicked -> Unit
        }
    }

}