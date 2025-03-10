package dev.phyo.burmobot.presentation.util

sealed class Screen(val route: String) {
    data object ChatBotScreen: Screen(route = "chatbot_screen")
    data object FavouritesScreen: Screen(route = "favourite_screen")
    data object RecentScreen: Screen(route = "recent_screen")
    data object AboutScreen: Screen(route = "about_screen")
}