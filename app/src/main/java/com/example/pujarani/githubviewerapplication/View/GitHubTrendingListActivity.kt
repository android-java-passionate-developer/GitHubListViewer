package com.example.pujarani.githubviewerapplication.View

import android.app.Application
import android.arch.lifecycle.*
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.MainThread
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.pujarani.githubviewerapplication.Model.GitDatabase
import com.example.pujarani.githubviewerapplication.Model.GitHubDao
import com.example.pujarani.githubviewerapplication.Model.GitHubDatModel
import com.example.pujarani.githubviewerapplication.R
import com.example.pujarani.githubviewerapplication.ViewModel.GitHubListViewModel
import com.example.pujarani.githubviewerapplication.ViewModel.Injection
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_git_hub_trending_list.*
import kotlinx.android.synthetic.main.layout_error.*
import android.widget.ArrayAdapter
import java.util.*


class GitHubTrendingListActivity() : AppCompatActivity(), LifecycleOwner, GithubtrendinglistAdapter.onClickViewItem {

    private lateinit var listAdapter: GithubtrendinglistAdapter
    private lateinit var viewmodel: GitHubListViewModel
    private var TAG: String = this.toString()
    private lateinit var lifeCycleReg: LifecycleRegistry
    private lateinit var gitlist: List<GitHubDatModel>


    override fun onClickCardView(data: GitHubDatModel) {
        val intent = Intent(this@GitHubTrendingListActivity, GitHubDetailsActivity::class.java)
        intent.putExtra("DATA", data)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_git_hub_trending_list)
        lifeCycleReg = LifecycleRegistry(this)
        lifeCycleReg.markState(Lifecycle.State.CREATED)

        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE


        var recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        var refreshLayout: SwipeRefreshLayout = findViewById(R.id.refresh)

        refreshLayout.setOnRefreshListener({
            Log.d("Puja", "refreshed")
            setUpViewModel();
            initialiseView();
            refreshLayout.isRefreshing = false
        })

        refreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        setUpViewModel();
        initialiseView();
    }

    private fun initialiseView() {

        recyclerView.layoutManager = LinearLayoutManager(this)

        listAdapter = GithubtrendinglistAdapter(gitlist, this)
        recyclerView.adapter = listAdapter
    }

    private var db: GitDatabase? = null
    private var dao: GitHubDao? = null

    private fun setUpViewModel() {
        viewmodel = ViewModelProviders.of(this, Injection.provideViewModelFactory()).get(GitHubListViewModel::class.java)
        viewmodel.isViewLoading.observe(this, isViewLoadingObserver)
//        viewmodel.onMessageError.observe(this,onMessageErrorObserver)
        viewmodel.isEmptyList.observe(this, emptyListObserver)

        gitlist = viewmodel.githubList.value!!
        if (gitlist!!.isEmpty()) {

            io.reactivex.Observable.fromCallable({
                db = GitDatabase.getDatabase(context = this)
                dao = db!!.gitHubDao()
                dao!!.getAllGitListSaved().observe(this, renderGitHubList)
            }).subscribeOn(Schedulers.io())
                    .subscribe()
            Toast.makeText(this, "Error in  Loading from server.", Toast.LENGTH_LONG).show();

        } else {
            viewmodel.githubList.observe(this, renderGitHubList)
            io.reactivex.Observable.fromCallable({
                db = GitDatabase.getDatabase(context = this)
                dao = db!!.gitHubDao()
                dao!!.insert(gitlist)
            }).subscribeOn(Schedulers.io())
                    .subscribe()
            Toast.makeText(this, "GitHub List loaded successfully", Toast.LENGTH_LONG).show();
        }

    }

    private val renderGitHubList = Observer<List<GitHubDatModel>> {
        Log.v("MainActivity", "data updated $it")
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        it?.let { it1 -> listAdapter.update(it1) }
    }


    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it!!) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        textViewError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
        Toast.makeText(this, "Empty: $it", Toast.LENGTH_LONG).show();
    }

    override fun onResume() {
        super.onResume()
        viewmodel.loadData()
    }

    override fun onStart() {
        super.onStart()

        lifeCycleReg.markState(Lifecycle.State.STARTED)
    }

    override fun getLifecycle(): Lifecycle {
        return lifeCycleReg
    }

}
