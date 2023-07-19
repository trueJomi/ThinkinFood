package com.thinkingfood.androidvovil.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thinkingfood.androidvovil.viewsModel.RecetaViewModel

class RecetaViewModelFactory(private val id:String) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecetaViewModel(id) as T
    }
}