package ca.on.listech.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.on.listech.borutoapp.util.Constants

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController, Screen.Splash.route) {
        composable(Screen.Splash.route) {

        }
        composable(Screen.Welcome.route) {

        }
        composable(Screen.Home.route) {

        }
        composable(
            Screen.Details.route,
            arguments = listOf(navArgument(Constants.DETAILS_ARG_KEY) {
                NavType.IntType
            })
        ) {

        }
        composable(Screen.Search.route) {

        }
    }
}