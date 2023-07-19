package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class Insumo(
    @DocumentId var id:String?,
    @PropertyName("nombre") var nombre:String?,
    @PropertyName("caducidad") var caducidad:Float?,
    @PropertyName("unidadMedida") var unidadMedida:DocumentReference?
): Serializable{
    constructor(doc: DocumentSnapshot): this(
        id= doc.id,
        nombre= doc.getString("nombre"),
        caducidad = doc.getDouble("caducidad")?.toFloat(),
        unidadMedida =doc.getDocumentReference("unidadMedida")
    )
}