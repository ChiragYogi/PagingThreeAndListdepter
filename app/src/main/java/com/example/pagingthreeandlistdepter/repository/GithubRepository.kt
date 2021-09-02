package com.example.pagingthreeandlistdepter.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pagingthreeandlistdepter.adepter.RepoPaginfgSource
import com.example.pagingthreeandlistdepter.api.GithubClient

class GithubRepository(private val client: GithubClient) {

     fun searchForRepo(query:String) =
         Pager(
             config = PagingConfig(
                 pageSize = 20,
                 maxSize = 100,
                 enablePlaceholders = false
             ),
             pagingSourceFactory = {RepoPaginfgSource(client,query)}
         ).liveData


}