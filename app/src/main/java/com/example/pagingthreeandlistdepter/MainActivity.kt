package com.example.pagingthreeandlistdepter

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingthreeandlistdepter.adepter.RepoLoadStateAdepter
import com.example.pagingthreeandlistdepter.api.GithubClient
import com.example.pagingthreeandlistdepter.databinding.ActivityMainBinding
import com.example.pagingthreeandlistdepter.repository.GithubRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RepoViewModel
    private val  mAdapter = RepoPagindAdepter()

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val repository = GithubRepository(GithubClient())
        val viewModelProviderFactery =RepoViewModelPriovder(repository)
        viewModel = ViewModelProvider(this,viewModelProviderFactery).get(RepoViewModel::class.java)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.apply {

            rvView.layoutManager = LinearLayoutManager(this@MainActivity)
            rvView.setHasFixedSize(true)
            rvView.addItemDecoration(decoration)
            rvView.itemAnimator = null
            rvView.adapter =mAdapter.withLoadStateHeaderAndFooter(
                header = RepoLoadStateAdepter{mAdapter.retry()},
                footer = RepoLoadStateAdepter{mAdapter.retry()}
            )

             buttnRetry.setOnClickListener{
                        mAdapter.retry()
                    }


            searchButton.setOnClickListener {


                hideSoftKeyboard()
                  if (searchEdt.text.isNotEmpty()){

                      rvView.scrollToPosition(0)
                      viewModel.searchRepo(searchEdt.text.toString())

                    }else{

                      Toast.makeText(this@MainActivity,
                          "Please Enter Your Query",Toast.LENGTH_LONG).show()
                    }
                }
        }


            mAdapter.addLoadStateListener { loadStates ->

                binding.apply {
                    progressBar.isVisible = loadStates.source.refresh is LoadState.Loading
                    rvView.isVisible = loadStates.source.refresh is LoadState.NotLoading
                    buttnRetry.isVisible = loadStates.source.refresh is LoadState.Error
                    textViewErrror.isVisible = loadStates.source.refresh is LoadState.Error

                    //empty view
                    if (loadStates.source.refresh is LoadState.NotLoading &&
                        loadStates.append.endOfPaginationReached &&
                        mAdapter.itemCount < 1
                    ) {
                        rvView.isVisible = false
                        textViewEmpty.isVisible = true
                    } else {
                        textViewEmpty.isVisible = false
                    }
                }
            }

        viewModel.repo.observe(this,{
            mAdapter.submitData(lifecycle,it)
        })
    }

    //hiding the keyboard
    private fun hideSoftKeyboard(){

        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken,0)

    }







}