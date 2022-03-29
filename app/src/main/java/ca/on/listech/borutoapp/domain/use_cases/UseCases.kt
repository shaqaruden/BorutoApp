package ca.on.listech.borutoapp.domain.use_cases

import ca.on.listech.borutoapp.domain.use_cases.get_onboarding.GetOnBoardingUseCase
import ca.on.listech.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val getOnBoardingUseCase: GetOnBoardingUseCase
)