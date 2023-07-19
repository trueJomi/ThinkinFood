package com.thinkingfood.androidvovil.Repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.thinkingfood.androidvovil.model.Receta

class RecetaRepositoy {

    private val TAG = "Error Receta"

    val fire = FirebaseFirestore.getInstance()

    fun getReceta (id:String,setReceta:(resenta: Receta)->Unit ){
        val ref =fire.collection("Recetas").document(id)
        ref.addSnapshotListener {  document, error->
            if (error != null){
                Log.w(TAG,"Listen List Recetas failed",error)
            }
            if ( document !=null && document.exists() ){
                val receta = Receta(document)
                setReceta(receta)
            }
        }
    }

    fun getListRecetas( actionReceta:(List<Receta>)->Unit): ListenerRegistration {
        val refDate = fire.collection("Recetas")
//        val interval= getIntervalMonth()
//        refDate.whereLessThanOrEqualTo("fecha",interval[1]).whereGreaterThanOrEqualTo("fecha",interval[0])

        val listen =refDate.addSnapshotListener { snapshot, error ->
            if (error != null){
                Log.w(TAG,"Listen List Recetas failed",error)
            }
            if ( snapshot != null && !snapshot.isEmpty ){
                val listData= mutableListOf<Receta>()
                for (doc in snapshot){
                    val receta = Receta(doc)
                    listData.add(receta)
                }
                actionReceta(listData)
            } else{
                Log.d(TAG,"Curent data null")
            }
        }
        return listen
    }
}