package ca.on.listech.borutoapp.presentation.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ca.on.listech.borutoapp.R
import ca.on.listech.borutoapp.ui.theme.appBarBackground
import ca.on.listech.borutoapp.ui.theme.appBarContent

@Composable
fun HomeAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(R.string.home_title), color = MaterialTheme.colors.appBarContent) },
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_button))
            }
        },
        backgroundColor = MaterialTheme.colors.appBarBackground
    )
}