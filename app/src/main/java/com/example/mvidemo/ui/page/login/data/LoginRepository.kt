package com.example.mvidemo.ui.page.login.data

import com.example.mvidemo.base.Result
import com.example.mvidemo.ui.page.login.data.model.LoggedUserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.util.UUID
import kotlin.random.Random

/**
 * desc: LoginRepository
 * Author: fengqy
 * Version: V1.0.0
 * Create: 2024/1/11 14:32
 */
object LoginRepository {

    // in-memory cache of the loggedInUser object
    var user: LoggedUserInfo? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
    }

//    fun login(username: String, password: String): Result<LoggedInUserInfo> {
//        // handle login
//        val result = try {
//            val fakeUser = LoggedInUserInfo(UUID.randomUUID().toString(), "Jane Doe")
//            Result.Success(fakeUser)
//        } catch (e: Throwable) {
//            Result.Error(IOException("Error logging in", e))
//        }
//
//        if (result is Result.Success) {
//            setLoggedInUser(result.data)
//        }
//
//        return result
//    }

    fun login(username: String, password: String) = flow {
        try {
            val fakeUser = LoggedUserInfo(UUID.randomUUID().toString(), "Jane Doe")
            setLoggedUserInfo(fakeUser)
            emit(Result.Success(fakeUser))
        } catch (e: Throwable) {
            emit(Result.Error(IOException("Error logging in", e)))
        }

    }.flowOn(Dispatchers.IO)

    private fun setLoggedUserInfo(loggedUserInfo: LoggedUserInfo) {
        user = loggedUserInfo
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

}