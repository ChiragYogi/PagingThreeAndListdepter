package com.example.pagingthreeandlistdepter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.pagingthreeandlistdepter.repository.GithubRepository
import kotlinx.coroutines.flow.MutableSharedFlow

class RepoViewModel(private val repository:GithubRepository):ViewModel() {




  private val curruentQuery = MutableLiveData<String>()



    val repo = curruentQuery.switchMap{ query ->
        repository.searchForRepo(query).cachedIn(viewModelScope)
    }

    fun searchRepo(query:String){
            curruentQuery.value = query
    }






    }


