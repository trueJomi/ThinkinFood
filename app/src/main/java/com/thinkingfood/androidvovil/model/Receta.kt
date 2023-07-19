package com.thinkingfood.androidvovil.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.io.Serializable


data class Receta (
    val id: String?,
    val nombre:String?,
    val dificultad:Int?,
    val duracion:Int?,
    val descripcion:String?,
    val porciones:Int?,
    val linkVideo:String?,
    val linkImage:String?,
    val favorito:Boolean?,
    var listInsumos:List<DetalleListaCompra>?,
    var pasos:List<String>?,
) : Serializable{

    constructor():this(
        id = null,
        nombre= null,
        dificultad = null,
        duracion = null,
        descripcion= null,
        favorito=null,
        linkVideo = null,
        porciones = null,
        linkImage = null,
        listInsumos = arrayListOf(),
        pasos = arrayListOf()
    )

    constructor(doc: DocumentSnapshot) : this(
        id = doc.id,
        nombre=doc.getString("nombre"),
        dificultad = doc.getLong("dificultad")?.toInt(),
        duracion = doc.getLong("duracion")?.toInt(),
        descripcion = doc.getString("descripcion"),
        linkVideo = doc.getString("linkVideo"),
        favorito = doc.getBoolean("favorito"),
        porciones = doc.getLong("porciones")?.toInt(),
        linkImage = doc.getString("linkImage"),
        listInsumos = arrayListOf(),
        pasos = arrayListOf()
    ){
        val arrayPasosTemp = doc.get("pasos") as? ArrayList<String>
        if ( arrayPasosTemp != null){
            val tempList = mutableListOf<String>()
            for (paso in arrayPasosTemp){
                tempList.add(paso.toString())
            }
            this.pasos=tempList
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
            this.listInsumos=tempList
        }
    }

    fun toMap(): HashMap<String, Any?> {
        val newMap= hashMapOf(
            "nombre" to this.nombre,
            "dificultad" to this.dificultad,
            "duracion" to this.duracion,
            "descripcion" to this.descripcion,
            "linkVideo" to this.linkVideo,
            "linkImage" to this.linkImage,
            "listInsumos" to this.listInsumos,
            "pasos" to this.pasos,
        )
        return newMap
    }
}