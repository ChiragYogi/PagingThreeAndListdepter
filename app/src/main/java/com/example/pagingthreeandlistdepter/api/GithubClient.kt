package com.example.pagingthreeandlistdepter.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GithubClient {

    companion object{

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: GithubApiCall by lazy {

        retrofit.create(GithubApiCall::class.java)
    }
    

}