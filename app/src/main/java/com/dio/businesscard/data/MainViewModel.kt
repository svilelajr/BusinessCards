package com.dio.businesscard.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.security.PrivilegedAction

class MainViewModel(private val businessCardRepository: BusinessCardRepository): ViewModel() {



    fun insert(businessCard: BusinessCard){
        businessCardRepository.insert((businessCard))
    }

    fun getAll() : LiveData<List<BusinessCard>>{
        return businessCardRepository.getAll()
    }

}


class  MainViewModelFactory(private val repository: BusinessCardRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}