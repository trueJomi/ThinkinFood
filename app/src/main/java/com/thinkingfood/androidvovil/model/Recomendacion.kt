package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class Recomendacion(
    @DocumentId var id:String?,
    @PropertyName("titulo") val titulo:String,
    @PropertyName("descripcion") val descripcion:String,
) : Serializable {
}