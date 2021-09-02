package com.example.pagingthreeandlistdepter.adepter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.pagingthreeandlistdepter.databinding.LoadStateBinding

class RepoLoadStateAdepter(private val retry: () -> Unit):LoadStateAdapter
                                <RepoLoadStateAdepter.LoadStateViewHolder>() {


    inner class LoadStateViewHolder(private val binding: LoadStateBinding):
                                        RecyclerView.ViewHolder(binding.root){

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }
        fun bind(loadState: LoadState){

            if (loadState is LoadState.Error){
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible = loadState is LoadState.Error
            }
        }

    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadStateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return LoadStateViewHolder(binding)
    }
}