package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.PropertyName
import java.io.Serializable
import java.util.Date

data class ListaCompra(
    @DocumentId val id:String?,
    @PropertyName("fecha") val fecha:Date?,
    @PropertyName("usuario") val usuario:DocumentReference?,
    @PropertyName("recetas") var recetas : List<DocumentReference>,
    @PropertyName("contenido") var contenido : List<DetalleListaCompra>
): Serializable{

    constructor() : this(
        id = null,
        fecha = null,
        usuario = null,
        recetas = arrayListOf(),
        contenido = arrayListOf()
    )

    constructor (doc: DocumentSnapshot) : this(
        id = doc.id,
        fecha = doc.getDate("fecha"),
        usuario = doc.getDocumentReference("usuario"),
        recetas = arrayListOf(),
        contenido = arrayListOf()
    ){
        val arrayRecetasTemp = doc.get("recetas") as? ArrayList<DocumentReference>
        if (arrayRecetasTemp != null){
            val data = mutableListOf<DocumentReference>()
            for ( doc in arrayRecetasTemp ){
                data.add(doc)
            }
            this.recetas=data
        }
        val mapInsumosTemp = doc.get("listInsumos") as? ArrayList<Map<String,Any>>
        if ( mapInsumosTemp != null){
            val tempList = mutableListOf<DetalleListaCompra>()
            for (insumo in mapInsumosTemp){
                val cant = insumo.get("cantidad").toString().toInt()
                val ins =  insumo.get("insumo") as? DocumentReference
                if ( ins != null ) {
                    tempList.add(DetalleListaCompra(ins,cant))
                }
            }
            this.contenido = tempList
        }
    }
}