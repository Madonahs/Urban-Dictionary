package com.madonasyombua.dictionaryurban.tests


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.madonasyombua.dictionaryurban.R
import com.madonasyombua.dictionaryurban.ui.DictionaryActivity
import com.madonasyombua.dictionaryurban.ui.MainActivity
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    //Needed rule to start UI
    @get:Rule
    val activityTestRule = ScreenUnitTestRule(
        activityClass = MainActivity::class.java,
        navigateToScreen = {}
    )
    @Before
    @Throws(Exception::class)
    fun intentsInit(){
        // initialize Espresso Intents capturing
        Intents.init()
    }

    @Test
    fun verifyOpenDictionaryOpensDictionaryActivity() {
        onView(withId(R.id.open_dictionary_button))
            .perform(click())
        // New activity should be launched
        intended(hasComponent(DictionaryActivity::class.java.name))
    }

    @After
    @Throws(Exception::class)
    fun intentsTeardown() {
        // release Espresso Intents capturing
        Intents.release()
    }


}