package com.example.mvidemo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * desc: BaseViewModel
 * Author: fengqy
 * Version: V1.0.0
 * Create: 2024/1/11 10:25
 */
abstract class BaseViewModel<A : IAction, S : IState, E : IEvent> : ViewModel() {
    private val _action = Channel<A>()

    init {
        viewModelScope.launch {
            _action.consumeAsFlow().collect {
                /** replayState：很多时候我们需要通过上个state的数据来处理这次数据，所以我们要获取当前状态传递*/
                onAction(it, _state.replayCache.firstOrNull())
            }
        }
    }

    /** [action] 用于在非 viewModelScope 外使用*/
    val action: SendChannel<A> by lazy { _action }

    fun sendAction(action: A) = viewModelScope.launch {
        _action.send(action)
    }

    /** 订阅事件的传入 onAction() 分发处理事件 */
    protected abstract fun onAction(action: A, currentState: S?)

    /**
     * state 实现
     */

    /** 继承 BaseViewModel 需要实现 state 默认值*/
//    abstract fun initialState(): S
//
//    private val _state by lazy {
//        MutableStateFlow(value = initialState())
//    }
//
//    /**在view中用于订阅*/
//    val state: StateFlow<S> by lazy {
//        _state.asStateFlow()
//    }

    protected fun emitState(builder: suspend () -> S?) = viewModelScope.launch {
        builder()?.let { _state.emit(it) }
    }

    /** suspend 函数在 flow 或者 scope 中 emit 状态*/
    protected suspend fun emitState(state: S) = _state.emit(state)


    //     不想要默认值，可以使用 SharedFlow 实现
    private val _state =
        MutableSharedFlow<S>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    //    SharedFlow默认是不防抖的，借助函数kotlinx.coroutines.flow.distinctUntilChanged()
    val state: Flow<S> by lazy { _state.distinctUntilChanged() }


    /**
     * [event]事件，通常是一次性事件 例如：弹 Toast、导航 Fragment 等
     */
    private val _event = MutableSharedFlow<E>()
    val event: SharedFlow<E> by lazy { _event.asSharedFlow() }

    protected fun emitEvent(builder: suspend () -> E?) = viewModelScope.launch {
        builder()?.let { _event.emit(it) }
    }

    protected suspend fun emitEvent(event: E) = _event.emit(event)
}