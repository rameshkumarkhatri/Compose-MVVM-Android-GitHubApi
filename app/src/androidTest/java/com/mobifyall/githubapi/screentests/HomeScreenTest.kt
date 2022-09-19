package com.mobifyall.githubapi.screentests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.mobifyall.githubapi.BaseTest
import com.mobifyall.githubapi.R
import com.mobifyall.githubapi.commons.TAG_SEARCH_ICON
import org.junit.Test

class HomeScreenTest : BaseTest() {

    @Test
    fun test1ShowHomeScreen() {
        val noSearchTermText =
            context.resources.getString(R.string.no_recent_searches)
        composeTestRule.onNodeWithText(noSearchTermText).assertIsDisplayed()
    }

    @Test
    fun test2ShowHomeScreenSearchIcon() {
        composeTestRule.onNodeWithTag(TAG_SEARCH_ICON).assertIsDisplayed()
    }
}