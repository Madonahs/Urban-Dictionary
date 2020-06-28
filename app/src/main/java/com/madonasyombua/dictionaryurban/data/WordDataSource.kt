package com.madonasyombua.dictionaryurban.data

import com.madonasyombua.dictionaryurban.api.Api
import com.madonasyombua.dictionaryurban.api.ERROR_STATUS
import com.madonasyombua.dictionaryurban.api.safeApiCall
import com.madonasyombua.dictionaryurban.data.response.BaseResponse
import com.madonasyombua.dictionaryurban.data.response.Results
import com.madonasyombua.dictionaryurban.utils.ErrorHelper
import timber.log.Timber

/**
 * the data source class gets the APi
 */
class WordDataSource(private  val api: Api){
    suspend fun definition(word: String) = safeApiCall(
        call={getDefinition(word)}
    )
    // get definition
    private suspend fun getDefinition(word: String): Results<BaseResponse> {
        Timber.d("MainViewModel_TAG: getDefinition: ")
        api.getDefinitionAsync(word).let {
            if (!it.whenEmpty()) {
                return Results.Success(it)
            }
            return Results.Error(ErrorHelper(ERROR_STATUS.ERR))
        }
    }

}