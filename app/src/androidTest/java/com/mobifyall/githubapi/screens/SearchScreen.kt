package com.mobifyall.githubapi.screens

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.mobifyall.githubapi.commons.TAG_SEARCH_ICON
import com.mobifyall.githubapi.commons.TAG_SEARCH_INPUT

class SearchScreen(private val rule: ComposeTestRule) {

    fun searchRepo(text: String) {
        rule.onNodeWithTag(TAG_SEARCH_INPUT).performTextInput(text)
        rule.onNodeWithTag(TAG_SEARCH_INPUT).performImeAction()
    }

    companion object {
        const val searchTerm = "google"
        const val wrongSearchTerm = "thriv1"
    }
}