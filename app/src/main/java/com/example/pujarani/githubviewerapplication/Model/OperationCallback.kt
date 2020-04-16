package com.example.pujarani.githubviewerapplication.Model

/**
 * Created by Puja.Rani on 14-04-2020.
 */
interface OperationCallback<T> {
        fun onSuccess(data:List<T>?)
        fun onError(error:String?)
}