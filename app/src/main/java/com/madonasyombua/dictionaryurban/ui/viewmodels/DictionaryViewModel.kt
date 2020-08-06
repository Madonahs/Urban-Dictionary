package com.madonasyombua.dictionaryurban.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.madonasyombua.dictionaryurban.data.repository.Repository
import com.madonasyombua.dictionaryurban.data.response.BaseResponse
import com.madonasyombua.dictionaryurban.data.response.Results
import com.madonasyombua.dictionaryurban.data.response.Word
import com.madonasyombua.dictionaryurban.utils.ErrorHelper
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author Madona
 *  The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way and
 *  For simple data, the activity can use the onSaveInstanceState() method and restore its data from the bundle
 *  in onCreate(), but this approach is only suitable for small amounts of data that can be serialized then
 *  deserialized, not for potentially large amounts of data like a list of users or bitmaps.
 */

class DictionaryViewModel(private val  useCases: Repository): ViewModel() {
    private val mutableLiveData = MutableLiveData<List<Word>>()

    val uiModel: LiveData<List<Word>>
        get() = mutableLiveData

    fun getDefinition(word: String) {
        progress.postValue(true)
        launchDefinition(word)
    }

    private fun launchDefinition(word: String) =
        viewModelScope.launch {
        val result = useCases(word)
       onResult(result)
    }

    private fun onResult(results: Results<BaseResponse>) {
        progress.postValue(false)
        when (results) {
            is Results.Success -> {
               mutableLiveData.postValue(results.data.wordList)
            }
            is Results.Error -> {
                errorBase.postValue(results.exception as ErrorHelper?)
            }
        }
    }

    val errorBase = MutableLiveData<ErrorHelper>()
    val progress = MutableLiveData<Boolean>()
    fun progress(): MutableLiveData<Boolean> {
        return progress
    }

}

