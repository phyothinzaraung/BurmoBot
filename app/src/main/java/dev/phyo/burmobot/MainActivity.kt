package dev.phyo.burmobot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.phyo.burmobot.presentation.aboutscreen.ui.AboutScreen
import dev.phyo.burmobot.presentation.util.Screen
import dev.phyo.burmobot.presentation.chatboscreen.ui.ChatbotScreen
import dev.phyo.burmobot.presentation.chatboscreen.viewmodel.ChatBotViewModel
import dev.phyo.burmobot.presentation.favouritescreen.ui.FavouriteScreen
import dev.phyo.burmobot.presentation.favouritescreen.viewmodel.FavouriteViewModel
import dev.phyo.burmobot.presentation.recentscreen.ui.RecentScreen
import dev.phyo.burmobot.presentation.recentscreen.viewmodel.RecentViewModel
import dev.phyo.burmobot.presentation.recentscreen.viewmodel.RecentViewModel_Factory
import dev.phyo.burmobot.ui.theme.BurmoBotTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BurmoBotTheme {
                AppNavigation(navController = rememberNavController())
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController =  navController, startDestination = Screen.ChatBotScreen.route){
        composable(route = Screen.ChatBotScreen.route) {
            ChatbotScreen(
                chatBotViewModel = hiltViewModel<ChatBotViewModel>(),
                favouriteViewModel = hiltViewModel<FavouriteViewModel>(),
                recentViewModel = hiltViewModel<RecentViewModel>(),
                navController
            )
        }
        composable(route = Screen.FavouritesScreen.route) {
            FavouriteScreen()
        }
        composable(route = Screen.RecentScreen.route) {
            RecentScreen()
        }
        composable(route = Screen.AboutScreen.route) {
            AboutScreen()
        }
    }
}

