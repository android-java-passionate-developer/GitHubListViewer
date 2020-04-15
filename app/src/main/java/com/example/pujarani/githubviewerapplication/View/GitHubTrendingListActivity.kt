package com.example.pujarani.githubviewerapplication

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.pujarani.githubviewerapplication.Adapter.GithubtrendinglistAdapter
import com.example.pujarani.githubviewerapplication.databinding.ActivityGitHubTrendingListBinding

class GitHubTrendingListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGitHubTrendingListBinding
    private lateinit var listAdapter : GithubtrendinglistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialiseViewModel();
        initialiseView();
    }

    private fun initialiseView(){

        binding = DataBindingUtil.setContentView<ActivityGitHubTrendingListBinding>(this, R.layout.activity_git_hub_trending_list)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        listAdapter = GithubtrendinglistAdapter(this)
        binding.recyclerView.adapter = listAdapter
//        binding.recyclerView.addOnScrollListener(object :)


    }

    private fun initialiseViewModel(){

    }

}
