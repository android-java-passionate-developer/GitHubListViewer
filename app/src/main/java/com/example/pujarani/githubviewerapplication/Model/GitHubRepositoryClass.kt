package com.example.pujarani.githubviewerapplication.Model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Puja.Rani on 14-04-2020.
 */
class GitHubRepositoryClass(): GitHubDataInterface{

    private var call: Call<List<GitHubDatModel>>?=null


    override fun retrieveGitHubList(callback: OperationCallback<GitHubDatModel>) {
        call=ApiClient.build()?.getList()

        call?.enqueue(object: Callback<List<GitHubDatModel>>{
            override fun onResponse(call: Call<List<GitHubDatModel>>?, response: Response<List<GitHubDatModel>>?) {
                response?.body()?.let {
                    if(response.isSuccessful){
                        callback.onSuccess(it)
                    }else{
                        callback.onError(response.message())
                    }
                }
            }

            override fun onFailure(call: Call<List<GitHubDatModel>>?, t: Throwable?) {
                callback.onError(t?.message)

            }

        })
    }


        override fun cancel() {
            call?.let {
                it.cancel()
            }
        }
    }