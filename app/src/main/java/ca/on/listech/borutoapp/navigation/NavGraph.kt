package ca.on.listech.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.on.listech.borutoapp.presentation.screens.home.HomeScreen
import ca.on.listech.borutoapp.presentation.screens.splash.SplashScreen
import ca.on.listech.borutoapp.presentation.screens.welcome.WelcomeScreen
import ca.on.listech.borutoapp.util.Constants
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController, Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen()
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