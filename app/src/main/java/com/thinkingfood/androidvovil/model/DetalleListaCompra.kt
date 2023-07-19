package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class DetalleListaCompra(
    @PropertyName("insumo") val insumo: DocumentReference?,
    @PropertyName("cantidad") val cantidad: Int?
)