package ca.on.listech.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

import ca.on.listech.borutoapp.data.local.BorutoDB
import ca.on.listech.borutoapp.data.paging_source.HeroRemoteMediator
import ca.on.listech.borutoapp.data.remote.BorutoAPI
import ca.on.listech.borutoapp.domain.model.Hero
import ca.on.listech.borutoapp.domain.repository.RemoteDataSource
import ca.on.listech.borutoapp.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(private val borutoAPI: BorutoAPI, private val borutoDB: BorutoDB): RemoteDataSource {
    private val heroDao = borutoDB.heroDao()

    override fun getHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getHeros() }
        return Pager(
            config = PagingConfig(pageSize =  ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(borutoAPI, borutoDB),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}