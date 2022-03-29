package ca.on.listech.borutoapp.di

import android.content.Context
import ca.on.listech.borutoapp.data.repository.DataStoreOperationsImpl
import ca.on.listech.borutoapp.data.repository.Repository
import ca.on.listech.borutoapp.domain.repository.DataStoreOperations
import ca.on.listech.borutoapp.domain.use_cases.UseCases
import ca.on.listech.borutoapp.domain.use_cases.get_onboarding.GetOnBoardingUseCase
import ca.on.listech.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideDataStoreOperations(@ApplicationContext cxt: Context): DataStoreOperations {
        return DataStoreOperationsImpl(cxt)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            getOnBoardingUseCase = GetOnBoardingUseCase(repository)
        )
    }
}