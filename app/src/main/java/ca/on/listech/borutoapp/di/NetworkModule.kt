package ca.on.listech.borutoapp.di

import androidx.paging.ExperimentalPagingApi
import ca.on.listech.borutoapp.data.local.BorutoDB
import ca.on.listech.borutoapp.data.remote.BorutoAPI
import ca.on.listech.borutoapp.data.repository.RemoteDataSourceImpl
import ca.on.listech.borutoapp.domain.repository.RemoteDataSource
import ca.on.listech.borutoapp.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(httpClient: OkHttpClient): Retrofit {
        val contentType = MediaType.get("application/json")

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providesBorutoAPI(retrofit: Retrofit): BorutoAPI {
        return retrofit.create(BorutoAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(borutoAPI: BorutoAPI, borutoDB: BorutoDB): RemoteDataSource {
        return RemoteDataSourceImpl(borutoAPI, borutoDB)
    }
}