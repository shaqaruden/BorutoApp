package ca.on.listech.borutoapp.domain.repository

import androidx.paging.PagingData
import ca.on.listech.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>
}