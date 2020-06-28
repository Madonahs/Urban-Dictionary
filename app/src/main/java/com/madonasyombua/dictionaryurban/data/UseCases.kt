package com.madonasyombua.dictionaryurban.data

import com.madonasyombua.dictionaryurban.data.repository.Repository
import com.madonasyombua.dictionaryurban.data.response.BaseResponse
import com.madonasyombua.dictionaryurban.data.response.Results
import com.madonasyombua.dictionaryurban.data.response.Word

/***
 * @author madona
 * The purpose of the Use Cases is to request data to repositories and turn into something usable for the View.
 *
 */

class UseCases(private val dictionaryRepository: Repository) {
    suspend operator fun invoke(word: String): Results<BaseResponse> {
        return dictionaryRepository.definition(word)
    }
}