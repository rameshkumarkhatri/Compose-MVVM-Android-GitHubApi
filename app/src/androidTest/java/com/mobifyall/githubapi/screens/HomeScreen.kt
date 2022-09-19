package com.mobifyall.githubapi.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.mobifyall.githubapi.commons.TAG_SEARCH_ICON

class HomeScreen(private val rule: ComposeTestRule) {

    fun clickSearchIcon() {
        rule.onNodeWithTag(TAG_SEARCH_ICON).performClick()
    }
}