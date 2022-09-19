package com.mobifyall.githubapi.screentests

import androidx.compose.ui.test.*
import com.mobifyall.githubapi.BaseTest
import com.mobifyall.githubapi.commons.*
import com.mobifyall.githubapi.screens.HomeScreen
import com.mobifyall.githubapi.screens.SearchScreen
import com.mobifyall.githubapi.ui.screens.HomeScreen
import org.junit.Before
import org.junit.Test

class SearchScreenTest : BaseTest() {
    lateinit var homeScreen: HomeScreen
    lateinit var searchScreen: SearchScreen

    @Before
    fun setup() {
        homeScreen = HomeScreen(composeTestRule)
        searchScreen = SearchScreen(composeTestRule)
    }

    @Test
    fun test1ShowSearchScreen() {
        homeScreen.clickSearchIcon()
        composeTestRule.onNodeWithTag(TAG_SEARCH_INPUT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TAG_BACK_NAVIGATION_ICON).assertIsDisplayed()
    }

    @Test
    fun test2SearchTermAddOnly(){
        homeScreen.clickSearchIcon()
        searchScreen.searchRepo(SearchScreen.searchTerm)
        composeTestRule.onNodeWithTag(TAG_LIST, useUnmergedTree = true).assertDoesNotExist()
    }

    @Test
    fun test2SearchResultWith3Results(){
        homeScreen.clickSearchIcon()
        searchScreen.searchRepo(SearchScreen.searchTerm)
        Thread.sleep(Companion.DEFAULT_WAIT)
        composeTestRule.onNodeWithTag(TAG_LIST, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onAllNodesWithTag(TAG_ROW_REPO, useUnmergedTree = true)[2].assertIsDisplayed()
    }

    @Test
    fun test2SearchResultNoResult(){
        homeScreen.clickSearchIcon()
        searchScreen.searchRepo(SearchScreen.wrongSearchTerm)
        Thread.sleep(Companion.DEFAULT_WAIT)
        composeTestRule.onNodeWithTag(TAG_LIST, useUnmergedTree = true).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TAG_ERROR_MESSAGE, useUnmergedTree = true).assertIsDisplayed()
    }
}