package com.mobifyall.githubapi

import android.content.Context
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.RuleChain

open class BaseTest {
    protected var uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    protected var context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun prepare() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun finish() {
        scenario.close()
    }

    val composeTestRule = createEmptyComposeRule()

    @Rule
    @JvmField
    var ruleChain = RuleChain.outerRule(composeTestRule)


    companion object {
        const val DEFAULT_WAIT: Long = 2000
    }
}