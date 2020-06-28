package com.madonasyombua.dictionaryurban.tests

import android.app.Activity
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.madonasyombua.dictionaryurban.App

class ScreenUnitTestRule<T : Activity>(
    activityClass: Class<T>,
    private val navigateToScreen: T.() -> Unit,
    private val setupMocks: () -> Unit = {}
) : ActivityTestRule<T>(activityClass) {
    @ExperimentalStdlibApi
    override fun beforeActivityLaunched() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        (instrumentation.targetContext.applicationContext as App).run {
            setupMocks()
        }
    }

    override fun afterActivityLaunched() {
        activity.navigateToScreen()
    }
}