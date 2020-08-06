package com.madonasyombua.dictionaryurban

import androidx.lifecycle.viewModelScope
import com.madonasyombua.dictionaryurban.data.repository.Repository
import com.madonasyombua.dictionaryurban.data.response.Word
import com.madonasyombua.dictionaryurban.ui.viewmodels.DictionaryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

class DictionaryViewModelTest {
    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private lateinit var wordList : List<Word>
    private val term = "homie"

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }


    @Test
    fun getTermUrbanDictionaryAndReturnAListWithItems(){
        val urban = Mockito.mock(Repository::class.java)
        wordList = listOf(
            Word("Homie is a friend",4000,22,
                "Madona","Homie's are buddies",
                "2020-05-777","Homer",33),
            Word(
                "Random pal", 333,23,
                "Mao","Also means thirsty","2005-90-737",
                "animation",444
            )
        )
        val dictionaryViewModel = DictionaryViewModel(urban)
        dictionaryViewModel.viewModelScope.launch {
            Mockito.`when`(urban.invoke(term)).thenReturn(wordList)
        }
        dictionaryViewModel.getDefinition(term)
        assert(dictionaryViewModel.uiModel != null)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }
}

private fun <T> OngoingStubbing<T>.thenReturn(wordList: List<Word>) {

}


