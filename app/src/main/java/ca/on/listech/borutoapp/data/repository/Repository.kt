package ca.on.listech.borutoapp.data.repository

import androidx.paging.PagingData
import ca.on.listech.borutoapp.domain.model.Hero
import ca.on.listech.borutoapp.domain.repository.DataStoreOperations
import ca.on.listech.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
){
    fun getHeroes(): Flow<PagingData<Hero>> {
        return remote.getHeroes()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        return dataStore.saveOnBoardingState(completed)
    }

    fun getOnBoardingState(): Flow<Boolean> {
        return dataStore.getOnBoardingState()
    }
}