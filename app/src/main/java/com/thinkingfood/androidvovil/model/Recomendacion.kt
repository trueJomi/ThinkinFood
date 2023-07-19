package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class Recomendacion(
    @DocumentId var id:String?,
    @PropertyName("titulo") val titulo:String?,
    @PropertyName("descripcion") val descripcion:String?,
) : Serializable {
    fun toMap(): HashMap<String, String?> {
        val newMap= hashMapOf(
            "titulo" to this.titulo,
            "descripcion" to this.descripcion,
        )
        return newMap
    }
}