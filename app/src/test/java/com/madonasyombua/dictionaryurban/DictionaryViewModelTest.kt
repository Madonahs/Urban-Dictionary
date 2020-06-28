package com.madonasyombua.dictionaryurban

import com.madonasyombua.dictionaryurban.data.WordDataSource
import com.madonasyombua.dictionaryurban.data.response.BaseResponse
import com.madonasyombua.dictionaryurban.data.response.Word
import com.madonasyombua.dictionaryurban.ui.viewmodels.DictionaryViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DictionaryViewModelTest {

    @Mock
    lateinit var wordService: WordDataSource

    @InjectMocks
    private var dictionaryViewModel: DictionaryViewModel? = null

    @Before
    fun setUpMockito(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    suspend fun successfullyGetDefinitions(){
        val definition = Word("Home", 1222, 1, "Madona", "Homer", "monday", "hello",455)
        val definitionsList = arrayListOf(definition)
        val response = BaseResponse(definitionsList)
    }

}