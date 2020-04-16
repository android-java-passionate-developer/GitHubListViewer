package com.example.pujarani.githubviewerapplication.Model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.pujarani.githubviewerapplication.View.GitHubDetailsActivity

/**
 * Created by Puja.Rani on 15-04-2020.
 */
class DatabaseRepository() {

    var context: Context = GitHubDetailsActivity().applicationContext;
    var database : GitDatabase = GitDatabase.getDatabase(context)
    var gitdao : GitHubDao = database.gitHubDao()
    private var allNotes  = gitdao.getAllGitListSaved()


    fun getAllNotes(): LiveData<List<GitHubDatModel>> {
        return allNotes
    }

    fun insert(data: List<GitHubDatModel>) {
        gitdao.insert(data)
    }

}