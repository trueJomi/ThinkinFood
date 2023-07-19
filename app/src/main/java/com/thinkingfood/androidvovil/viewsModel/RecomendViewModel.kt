package com.thinkingfood.androidvovil.viewsModel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecomendViewModel : ViewModel() {

    val firestore= Firebase.firestore
    fun enviarRecomencaiones(titulo:String, descripcion:String){
        val data=hashMapOf(
            "titulo" to titulo,
            "descripcion" to descripcion
        )
        firestore.collection("Recomendaciones").add( data)
    }
}