package com.example.pujarani.githubviewerapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import com.example.pujarani.githubviewerapplication.databinding.GithubListItemBinding

/**
 * Created by Puja.Rani on 04-12-2019.
 */
class GithubtrendinglistAdapter(private val context: Context): RecyclerView.Adapter<GithubtrendinglistAdapter.GitHubListViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GitHubListViewHolder {

        var layoutInflater = LayoutInflater.from(context);

        var itemBinding = GithubListItemBinding.inflate(layoutInflater, parent, false);

        var itemViewHolder = GitHubListViewHolder(itemBinding)
         return itemViewHolder;
    }

    override fun getItemCount(): Int {
        return 0;
    }

    override fun onBindViewHolder(holder: GitHubListViewHolder?, position: Int) {

    }

    class GitHubListViewHolder(private val binding: GithubListItemBinding) : RecyclerView.ViewHolder(binding.root)
    {


    }
}