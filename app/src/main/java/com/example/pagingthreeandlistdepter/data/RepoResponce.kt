package com.example.pagingthreeandlistdepter.data

import com.google.gson.annotations.SerializedName

data class RepoResponce (
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repo> = emptyList(),
    val nextPage: Int? = null
)