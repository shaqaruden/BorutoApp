package ca.on.listech.borutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ca.on.listech.borutoapp.data.local.BorutoDB
import ca.on.listech.borutoapp.data.remote.BorutoAPI
import ca.on.listech.borutoapp.domain.model.Hero
import ca.on.listech.borutoapp.domain.model.HeroRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoAPI: BorutoAPI,
    private val borutoDB: BorutoDB
): RemoteMediator<Int,Hero>() {
    private val heroDao = borutoDB.heroDao()
    private val remoteKeysDao = borutoDB.heroRemoteKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int,Hero>): MediatorResult {
        try {
            val response = borutoAPI.getHeroes()
            if(response.heroes.isNotEmpty()) {
                borutoDB.withTransaction {
                    if(loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        remoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map {
                        HeroRemoteKeys(it.id, prevPage, nextPage)
                    }
                    remoteKeysDao.addAllRemoteKeys(keys)
                    heroDao.addHeroes(response.heroes)
                }
                return MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
            }
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

    }
}