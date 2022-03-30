package ca.on.listech.borutoapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    val heroes = homeViewModel.getHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeAppBar(onSearchClicked = {})
        }
    ) {

    }
}