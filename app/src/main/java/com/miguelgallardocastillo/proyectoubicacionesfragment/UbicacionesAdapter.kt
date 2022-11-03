package com.miguelgallardocastillo.proyectoubicacionesfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miguelgallardocastillo.proyectoubicacionesfragment.databinding.ListItemBinding

class UbicacionesAdapter (private val UbicacionClickedListener: (Ubicacion) -> Unit ) : RecyclerView.Adapter<UbicacionesAdapter.ViewHolder>() {

    var ubicaciones = emptyList<Ubicacion>()

    companion object{
        const val ACTION_VIEW = "ubicación seleccionada"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ubicacion = ubicaciones[position]
        holder.bind(ubicacion)
        holder.itemView.setOnClickListener{
            UbicacionClickedListener(ubicacion)
        }
    }

    override fun getItemCount(): Int {
        return ubicaciones.size
    }

    class ViewHolder (private val view: View): RecyclerView.ViewHolder(view){

        //Guardamos en una variable el binding de esta vista para acceder a los elementos.
        val binding = ListItemBinding.bind(view)

        fun bind (ubicacion : Ubicacion){
            binding.imagen.glide(ubicacion.imagen)
            binding.nombreUbicacion.text = ubicacion.nombre
        }
    }



}

fun ImageView.glide(url: String){
    Glide.with(this).load(url).into(this)

}