package com.example.pagingthreeandlistdepter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingthreeandlistdepter.data.Repo
import com.example.pagingthreeandlistdepter.databinding.ListItemBinding

class RepoPagindAdepter(): PagingDataAdapter<Repo, RepoPagindAdepter.ViewHolder>(ViewHolder.TaskDiffUtillCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = getItem(position)

        if (currentItem != null){
            holder.bind(currentItem)
        }
    }





    class ViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(item:Repo){
            binding.apply {

                repoName.text = item.fullName
                repoDescription.text = item.description
                repoLanguage.text = item.language
                repoFork.text = item.forks.toString()
                repoStar.text = item.stars.toString()

            }
            binding.root.setOnClickListener{


                    item.url.let {url ->
                          val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
                            binding.root.context.startActivity(intent)
                        }


            }
        }







    class TaskDiffUtillCallBack : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem == newItem
        }

    }




}
}