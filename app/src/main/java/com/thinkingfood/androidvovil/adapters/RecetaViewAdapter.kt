package com.thinkingfood.androidvovil.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thinkingfood.androidvovil.databinding.HomeElementoListaRescetaBinding
import com.thinkingfood.androidvovil.model.Receta

class RecetaHolder(
    val binding:HomeElementoListaRescetaBinding
    ):RecyclerView.ViewHolder(binding.root) {
        fun enalzar(receta:Receta,move:(id:String)->Unit){
            binding.apply {
                binding.listNombreReceta.text=receta.nombre
                binding.listaDescripcionReceta.text=receta.descripcion
                binding.buttonLeft.setOnClickListener {
                    move(receta.id!!)
                }
                binding.persons.text= "${receta.porciones} Porciones"
                binding.time.text="${receta.duracion} Minutos"
                Glide.with(itemView)
                    .load(receta.linkImage)
                    .into(binding.imageReceta)
            }
        }
    }

class RecetaAdapter(
    private val recetas:List<Receta>,
    private val onCrimenMove: (id:String)-> Unit
): RecyclerView.Adapter<RecetaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding = HomeElementoListaRescetaBinding.inflate(inflater,parent,false)
        return RecetaHolder(binding)
    }

    override fun getItemCount(): Int =recetas.size
    override fun onBindViewHolder(holder: RecetaHolder, position: Int) {
        val receta= recetas.get(position)
        holder.enalzar(receta,onCrimenMove)

    }


}