package com.example.pujarani.githubviewerapplication.ViewModel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.pujarani.githubviewerapplication.Model.GitHubDatModel
import com.example.pujarani.githubviewerapplication.Model.GitHubDataInterface

/**
 * Created by Puja.Rani on 14-04-2020.
 */
class GitHubViewModelFactory(private val repository:GitHubDataInterface, private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GitHubListViewModel(repository) as T
    }
}