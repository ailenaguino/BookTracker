package com.ailenaguino.booktracker

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
    data object SearchBookScreen: Screen("search_book_screen")
    data object AddBookManuallyScreen: Screen("add_book_manually_screen")
    data object LibraryScreen: Screen("library_screen")
}