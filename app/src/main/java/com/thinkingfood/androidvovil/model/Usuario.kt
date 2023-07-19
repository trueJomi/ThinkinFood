package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.PropertyName
import java.io.Serializable

data class Usuario(
    @DocumentId var id:String?,
    @PropertyName("nombre") val nombre:String?,
    @PropertyName("email") val email:String?,
    @PropertyName("img") val img:String?
) : Serializable {
    constructor(doc: DocumentSnapshot): this(
        id =doc.id,
        nombre = doc.getString("nombre"),
        email = doc.getString("email"),
        img = doc.getString("img")
    )
}