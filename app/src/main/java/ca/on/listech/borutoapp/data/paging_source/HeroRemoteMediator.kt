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
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKey(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(remoteKeys != null)
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(remoteKeys != null)
                    nextPage
                }
            }

            val response = borutoAPI.getHeroes(page)
            if (response.heroes.isNotEmpty()) {
                borutoDB.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        remoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    remoteKeysDao.addAllRemoteKeys(heroRemoteKeys = keys)
                    heroDao.addHeroes(heroes = response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKey(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeysDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyFirstItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { hero ->
                remoteKeysDao.getRemoteKey(hero.id)
            }
    }

    private suspend fun getRemoteKeyLastItem(state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { hero ->
                remoteKeysDao.getRemoteKey(hero.id)
            }
    }
}