package ca.on.listech.borutoapp.data.remote

import ca.on.listech.borutoapp.domain.model.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoAPI {
    @GET("/boruto/heroes")
    suspend fun allHeroes(
        @Query("page") page: Int = 1
    ): APIResponse

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): APIResponse
}