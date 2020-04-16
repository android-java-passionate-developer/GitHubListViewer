package com.example.pujarani.githubviewerapplication.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.NonNull
import android.util.Log
import android.widget.Toast
import com.example.pujarani.githubviewerapplication.Model.*
import org.jetbrains.annotations.NotNull

/**
 * Created by Puja.Rani on 13-04-2020.
 */
class GitHubListViewModel(private val repository: GitHubDataInterface) : ViewModel() {
    private val githublist = MutableLiveData<List<GitHubDatModel>>().apply { value = emptyList() }
    var githubList: LiveData<List<GitHubDatModel>> = githublist


    private val _isListLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isListLoading

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList


    fun loadData() {
        _isListLoading.postValue(true)
        repository.retrieveGitHubList(object : OperationCallback<GitHubDatModel> {
            override fun onError(error: String?) {
                Log.d("Puja", "error:" + error);
                _isListLoading.postValue(false)
                _onMessageError.postValue(error)
            }

            override fun onSuccess(data: List<GitHubDatModel>?) {
                Log.d("Puja", "success:" + data);
                _isListLoading.postValue(false)

                if (data != null) {
                    if (data.isEmpty()) {
                        _isEmptyList.postValue(true)
                    } else {
                        githublist.value = data
                    }
                }
            }
        })
    }

}