package com.example.pujarani.githubviewerapplication.ViewModel

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.example.pujarani.githubviewerapplication.Model.GitHubDataInterface
import com.example.pujarani.githubviewerapplication.Model.GitHubRepositoryClass

/**
 * Created by Puja.Rani on 14-04-2020.
 */
object Injection {
    private val museumDataSource:GitHubDataInterface = GitHubRepositoryClass()
    private val application: Application = Application()
    private val museumViewModelFactory = GitHubViewModelFactory(museumDataSource, application)

    fun providerRepository():GitHubDataInterface{
        return museumDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return museumViewModelFactory
    }
}