package ca.on.listech.borutoapp.domain.use_cases.get_heros

import androidx.paging.PagingData
import ca.on.listech.borutoapp.data.repository.Repository
import ca.on.listech.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getHeroes()
    }
}