package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class UnidadMedida(
    @DocumentId var id:String?,
    @PropertyName("nombre") val nombre:String?,
    @PropertyName("abreviatura") val abreviatura:String?,
    @PropertyName("unidadMedidaRegular") val unidadMedidaRegular: String?,
    @PropertyName("conversion") val convesion:Double?,
) : Serializable{
    constructor(doc: DocumentSnapshot):this(
        id=doc.id,
        nombre=doc.getString("nombre"),
        abreviatura= doc.getString("abreviatura"),
        unidadMedidaRegular = doc.getString("unidadMedidaRegular"),
        convesion = doc.getDouble("conversion")
    )
}