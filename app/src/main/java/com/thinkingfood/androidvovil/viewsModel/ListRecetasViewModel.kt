package com.thinkingfood.androidvovil.viewsModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.thinkingfood.androidvovil.Repositories.RecetaRepositoy
import com.thinkingfood.androidvovil.model.Receta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class ListRecetasViewModel:ViewModel() {

    private val _recetas:MutableStateFlow<List<Receta>> = MutableStateFlow(emptyList())

    val repository = RecetaRepositoy()

    val recetas: StateFlow<List<Receta>>
    get()= _recetas.asStateFlow()
    init {
        viewModelScope.launch {
            repository.getListRecetas {
                _recetas.value=it
            }
        }
    }

    fun getMisRecetas() {

    }
    

}