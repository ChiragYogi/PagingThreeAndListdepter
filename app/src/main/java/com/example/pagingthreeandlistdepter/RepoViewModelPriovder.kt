package com.example.pagingthreeandlistdepter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pagingthreeandlistdepter.repository.GithubRepository

class RepoViewModelPriovder(val reposetry: GithubRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoViewModel(reposetry) as T
    }
}