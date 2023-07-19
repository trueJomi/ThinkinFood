package com.thinkingfood.androidvovil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thinkingfood.androidvovil.databinding.HomeElementoListaRescetaBinding
import com.thinkingfood.androidvovil.model.Receta
import com.thinkingfood.androidvovil.ui.home.RecetaHolder

class MisRecetaHolder(
    val binding: HomeElementoListaRescetaBinding
): RecyclerView.ViewHolder(binding.root) {
    fun enalzar(receta: Receta){
        binding.apply {
            binding.listNombreReceta.text=receta.nombre
            binding.listaDescripcionReceta.text=receta.descripcion
            binding.persons.text= "${receta.porciones} Porciones"
            binding.time.text="${receta.duracion} Minutos"
            Glide.with(itemView)
                .load(receta.linkImage)
                .into(binding.imageReceta)
        }
    }
}

class MisRecetasViewAdapter(
    private val recetas:List<Receta>,
    ): RecyclerView.Adapter<MisRecetaHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MisRecetaHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = HomeElementoListaRescetaBinding.inflate(inflater,parent,false)
        return MisRecetaHolder(binding)
    }
    override fun getItemCount(): Int =recetas.size
    override fun onBindViewHolder(holder: MisRecetaHolder, position: Int) {
        val receta= recetas.get(position)
        holder.enalzar(receta)

    }
}