package ca.on.listech.borutoapp.domain.model

import androidx.annotation.DrawableRes

sealed class OnBoardingPage(
    @DrawableRes
    val images: Int,
    val title: String,
    val description: String
)
