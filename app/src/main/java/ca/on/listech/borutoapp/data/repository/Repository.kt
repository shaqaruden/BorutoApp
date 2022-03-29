package ca.on.listech.borutoapp.data.repository

import ca.on.listech.borutoapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations
){
    suspend fun saveOnBoardingState(completed: Boolean) {
        return dataStore.saveOnBoardingState(completed)
    }

    fun getOnBoardingState(): Flow<Boolean> {
        return dataStore.getOnBoardingState()
    }
}