package com.mobifyall.githubapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobifyall.githubapi.navigation.AppNav
import com.mobifyall.githubapi.ui.screens.HomeScreen
import com.mobifyall.githubapi.ui.screens.SearchScreen
import com.mobifyall.githubapi.ui.theme.GitHubApiTheme
import com.mobifyall.githubapi.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController, startDestination = AppNav.Home.deeplink
                    ) {
                        composable(AppNav.Home.deeplink) {
                            HomeScreen(navHostController = navController)
                        }
                        composable(AppNav.Search.deeplink) {
                            SearchScreen(
                                viewModel = viewModel
                            ) {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }
    }
}