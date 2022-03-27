package ca.on.listech.borutoapp.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    object Details: Screen("details_screen/{hero_id}") {
        fun passHeroID(hero_id: Int): String {
            return "details_screens/$hero_id"
        }
    }
    object Search: Screen("search_screen")
}