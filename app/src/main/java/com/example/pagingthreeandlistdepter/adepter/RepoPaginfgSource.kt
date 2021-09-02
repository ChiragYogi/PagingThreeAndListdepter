package com.example.pagingthreeandlistdepter.adepter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingthreeandlistdepter.api.GithubClient
import com.example.pagingthreeandlistdepter.data.Repo
import retrofit2.HttpException
import java.io.IOException

private const val GITHUB_REPO_PAGE_INDEX  = 1

class RepoPaginfgSource
    (private val client:GithubClient, private val query: String):PagingSource<Int,Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val position = params.key ?: GITHUB_REPO_PAGE_INDEX


        return try {
            val responce = client.api.searchForRepo(query ,position, params.loadSize)
            val respos = responce.items

            LoadResult.Page(
                data = respos,
                prevKey = if (position == GITHUB_REPO_PAGE_INDEX) null else position - 1,
                nextKey = if (respos.isEmpty()) null else position + 1,
            )

        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (excepition : HttpException){
            LoadResult.Error(excepition)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
           }
}