package ca.on.listech.borutoapp.domain.use_cases.get_onboarding

import ca.on.listech.borutoapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetOnBoardingUseCase( private val repository: Repository) {
    operator fun invoke(): Flow<Boolean> {
        return repository.getOnBoardingState()
    }
}