package ca.on.listech.borutoapp.domain.use_cases.save_onboarding

import ca.on.listech.borutoapp.data.repository.Repository

class SaveOnBoardingUseCase(private val repository: Repository) {
    suspend operator fun invoke(completed: Boolean) {
        return repository.saveOnBoardingState(completed)
    }
}