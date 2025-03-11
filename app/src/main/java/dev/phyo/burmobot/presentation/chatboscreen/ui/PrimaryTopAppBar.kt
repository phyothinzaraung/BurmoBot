package dev.phyo.burmobot.presentation.chatboscreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.phyo.burmobot.R
import dev.phyo.burmobot.presentation.util.Screen
import dev.phyo.burmobot.presentation.util.ToolbarTitle
import dev.phyo.burmobot.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTopAppBar(navController: NavHostController, modifier: Modifier = Modifier) {
    var menuExpanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Row {
                Icon(
                    painter = painterResource(R.drawable.bot),
                    contentDescription = "App Icon",
                    modifier = modifier.size(32.dp)
                        .padding(end = 8.dp),
                    tint = PrimaryColor
                )
                ToolbarTitle("Burmo Bot")
            }
        },
        actions = {
            IconButton(onClick = { menuExpanded = true }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = PrimaryColor
                )
            }
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false },
                modifier = modifier.background(Color.White)
            ) {
                DropdownMenuItem(text = { Text("Favorites") }, onClick = {
                    menuExpanded = false
                    navController.navigate(Screen.FavouritesScreen.route)
                })
                DropdownMenuItem(text = { Text("Recent") }, onClick = {
                    menuExpanded = false
                    navController.navigate(Screen.RecentScreen.route)
                })
                DropdownMenuItem(text = { Text("About") }, onClick = {
                    menuExpanded = false
                    navController.navigate(Screen.AboutScreen.route)
                })
            }
        }
    )
}