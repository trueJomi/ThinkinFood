package com.thinkingfood.androidvovil.viewsModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thinkingfood.androidvovil.Repositories.RecetaRepositoy
import com.thinkingfood.androidvovil.model.Receta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecetaViewModel(id:String): ViewModel() {

    private  val _receta:MutableStateFlow<Receta?> = MutableStateFlow(null)

    val repository = RecetaRepositoy()
    val receta:StateFlow<Receta?>
    get() = _receta.asStateFlow()
    init {
        viewModelScope.launch {
            repository.getReceta(id){
                _receta.value=it
            }
        }
    }
}