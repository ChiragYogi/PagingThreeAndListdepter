package com.example.pagingthreeandlistdepter.api

import com.example.pagingthreeandlistdepter.data.RepoResponce
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiCall {

    @GET("/search/repositories?sort=stars")
    suspend fun searchForRepo(
        @Query("q") query:String,
        @Query("page") page:Int,
        @Query("per_page") perPage: Int
    ):RepoResponce
}