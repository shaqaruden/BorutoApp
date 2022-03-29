package ca.on.listech.borutoapp.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ca.on.listech.borutoapp.domain.model.OnBoardingPage
import ca.on.listech.borutoapp.navigation.Screen
import ca.on.listech.borutoapp.ui.theme.*
import com.google.accompanist.pager.*

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(OnBoardingPage.First, OnBoardingPage.Second, OnBoardingPage.Third)

    val pagerState = rememberPagerState()

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.welcomeBackgroundColor)) {
        HorizontalPager(
            count = pages.size,
            modifier = Modifier.weight(10f),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) {
            PagerScreen(pages[it])
        }
        HorizontalPagerIndicator(
            pagerState,
            modifier = Modifier
                .align(CenterHorizontally)
                .weight(1f),
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            indicatorWidth = 12.dp,
            spacing = 8.dp,

        )
        FinishButton(pagerState = pagerState, numberOfPages = pages.size) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(true)
        }
    }
}

@Composable
fun PagerScreen(page: OnBoardingPage) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painterResource(id = page.image), page.title,
            Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f))
        Text(
            page.title,
            color = MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
        )
        Text(
            page.description,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.ExtraLarge)
                .padding(top = Padding.Small),
            color = MaterialTheme.colors.descriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalPagerApi
@Composable
fun FinishButton(pagerState: PagerState, numberOfPages: Int, onClick: () -> Unit) {
    val density = LocalDensity.current
    Row(
        Modifier
            .padding(horizontal = Padding.ExtraLarge)
            .padding(bottom = Padding.Small)
            .height(ButtonDefaults.MinHeight), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Center) {
        AnimatedVisibility(
            enter = slideInVertically{ with(density) { 50.dp.roundToPx() } },
            exit = slideOutVertically{ with(density) { 50.dp.roundToPx() } },
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == (numberOfPages - 1)
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text("Finish")
            }
        }
    }
}