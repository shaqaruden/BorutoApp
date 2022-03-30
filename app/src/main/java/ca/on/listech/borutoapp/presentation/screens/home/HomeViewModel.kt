package ca.on.listech.borutoapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import ca.on.listech.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
): ViewModel() {
    val getHeroes = useCases.getHeroesUseCase()
}