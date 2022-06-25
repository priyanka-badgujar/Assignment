package com.example.myapplication.api

import com.example.myapplication.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("/3/movie/popular")
    suspend fun getMoviesData(
        @Query("api_key") apiKey: String,
        @Query("language") s: String,
        @Query("page") page: String
    ): Response<MoviesResponse>
}