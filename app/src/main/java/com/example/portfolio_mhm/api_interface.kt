package com.example.portfolio_mhm

import retrofit2.http.GET
import retrofit2.http.Header

interface api_interface {
    @GET("/user/repos")
    suspend fun getRepositories(
        @Header("Authorization") token: String
    ): List<Repo_Data>
}