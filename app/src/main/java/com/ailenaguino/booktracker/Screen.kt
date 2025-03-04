package com.ailenaguino.booktracker

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home_screen")
    data object SearchBookScreen: Screen("search_book_screen")
    data object AddBookManuallyScreen: Screen("add_book_manually_screen")
    data object LibraryScreen: Screen("library_screen")
    data object ReadLaterScreen: Screen("read_later_screen")
    data object GaveUpScreen: Screen("gave_up_screen")
    data object BookDetailScreen: Screen("book_detail_screen")
    data object SaveLectureScreen: Screen("save_lecture_screen")
    data object StoppedScreen: Screen("stopped_screen")


}