package com.madonasyombua.dictionaryurban.api

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.madonasyombua.dictionaryurban.data.response.Results
import com.madonasyombua.dictionaryurban.data.response.Word
import com.madonasyombua.dictionaryurban.ui.DefinitionActivity
import com.madonasyombua.dictionaryurban.utils.Constants
import com.madonasyombua.dictionaryurban.utils.ErrorHelper
import retrofit2.HttpException
import java.io.IOException

/**
 * @author madona
 * Suspending functions can be created as standard Kotlin functions, but we need to be aware that we can only call them from within a coroutine.
 * the suspend modifier tells the compiler that this function needs to be executed inside a coroutine
 * as a developer it is okay to assume that the process will be suspended and resumed as some point
 **/

suspend fun <T : Any> safeApiCall(call: suspend () -> Results<T>): Results<T> {
    return try {
        call()
    } catch (e: Exception) {
        when (e) {
            is IOException -> {
                Results.Error(ErrorHelper(ERROR_STATUS.NETWORK))
            }
            is HttpException -> {
                Results.Error(ErrorHelper(ERROR_STATUS.UNKNOWN))
            }
            else -> {
                Results.Error(ErrorHelper(ERROR_STATUS.ERR, e.message))
            }
        }
    }
}

val <T> T.exhaustive: T
    get() = this

// live data observer
fun <D : Any, T : LiveData<D>> T.observeWith(owner: LifecycleOwner, receiver: (D) -> Unit) {
    observe(owner, Observer<D> {
        if (it != null) {
            receiver(it)
        }
    })
}

// the enum for error status, also note enum classes only allow a single instance of each value and can not encode
//more information on each type
enum class ERROR_STATUS {
    NETWORK,
    UNKNOWN,
    ERR
}

fun Context.Intent1(word: Word): Intent {
    return Intent(this, DefinitionActivity::class.java).apply {
        val wordJsonBody = Gson().toJson(word)
        putExtra(Constants.INTENT_WORD_JSON, wordJsonBody)
    }
}

