package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentSnapshot

data class Restriccion(
    val id: String?,
    val query:String?,
    var restriciones:List<String>,
    var preferencias:List<String>,
    val tiempo_limite:String?,
) {
    constructor():this(
        id = null,
        query= null,
        restriciones = arrayListOf(),
        preferencias = arrayListOf(),
        tiempo_limite =null
    )

    constructor(doc: DocumentSnapshot):this(
        id = doc.id,
        query= doc.getString("query"),
        restriciones = arrayListOf(),
        preferencias = arrayListOf(),
        tiempo_limite =doc.getString("tiempoLimite")
    ){
        val arrayRestricciones = doc.get("restriciones") as? ArrayList<String>

        val arrayPreferencias = doc.get("preferencias") as? ArrayList<String>
    }
}