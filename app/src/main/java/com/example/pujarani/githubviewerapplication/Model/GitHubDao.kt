package com.example.pujarani.githubviewerapplication.Model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by Puja.Rani on 15-04-2020.
 */
@Dao
interface GitHubDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(data: List<GitHubDatModel>)

    @Query("SELECT * FROM Git")
    abstract fun getAllGitListSaved(): LiveData<List<GitHubDatModel>>
}