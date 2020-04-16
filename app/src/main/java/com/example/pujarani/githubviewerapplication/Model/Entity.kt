package com.example.pujarani.githubviewerapplication.Model

/**
 * Created by Puja.Rani on 14-04-2020.
 */
data class DataResponse(val status:Int?,val msg:String?,val data:List<GitHubDatModel>?){
    fun isSuccess():Boolean= (status==200)
}