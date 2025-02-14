package com.ailenaguino.booktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ailenaguino.booktracker.common.Constants
import com.ailenaguino.booktracker.feature_add_book.presentation.AddBookScreen
import com.ailenaguino.booktracker.feature_home.presentation.HomeScreen
import com.ailenaguino.booktracker.feature_library.presentation.LibraryScreen
import com.ailenaguino.booktracker.feature_read_later.presentation.ReadLaterScreen
import com.ailenaguino.booktracker.feature_search_book.presentation.SearchBookScreen
import com.ailenaguino.booktracker.ui.theme.BookTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(
                            route = Screen.HomeScreen.route
                        ) {
                            HomeScreen(navController)
                        }
                        composable(
                            route = Screen.SearchBookScreen.route
                        ) {
                            SearchBookScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddBookManuallyScreen.route
                        ) {
                            AddBookScreen(navController)
                        }
                        composable(
                            route = Screen.HomeScreen.route + "/{${Constants.PARAM_BOOK_ID}}"
                        ) {
                            HomeScreen(navController)
                        }
                        composable(
                            route = Screen.AddBookManuallyScreen.route + "/{${Constants.PARAM_BOOK}}",
                            arguments = listOf(navArgument(Constants.PARAM_BOOK) {type = NavType.StringType})
                        ) {
                            AddBookScreen(navController)
                        }
                        composable(
                            route = Screen.LibraryScreen.route
                        ) {
                            LibraryScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ReadLaterScreen.route
                        ) {
                            ReadLaterScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}