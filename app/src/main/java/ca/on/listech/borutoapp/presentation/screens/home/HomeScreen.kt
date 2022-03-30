package ca.on.listech.borutoapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeAppBar(onSearchClicked = {})
        }
    ) {

    }
}