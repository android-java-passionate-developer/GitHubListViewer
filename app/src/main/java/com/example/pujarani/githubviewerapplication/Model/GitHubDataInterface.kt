package com.example.pujarani.githubviewerapplication.Model

/**
 * Created by Puja.Rani on 14-04-2020.
 */
interface GitHubDataInterface {
    fun retrieveGitHubList(callback: OperationCallback<GitHubDatModel>)
    fun cancel()
}