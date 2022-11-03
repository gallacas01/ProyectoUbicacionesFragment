package com.miguelgallardocastillo.proyectoubicacionesfragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.miguelgallardocastillo.proyectoubicacionesfragment.databinding.FragmentListaUbicacionesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.reflect.Array.get


class ListaUbicacionesFragment : Fragment(R.layout.fragment_lista_ubicaciones) {

    private val adapter = UbicacionesAdapter(){ Ubicacion -> Unit}
    private lateinit var binding : FragmentListaUbicacionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //El escribir apply lo que hace es que no sea necesario escribir en el bloque de código que se abre el nombre de la variable.
        //En el siguiente ejemplo,al escribir apply, no hay que poner binding  = binding.recyclerViewUbicaciones.adapter = adapter, sino
        // directamente recyclerViewUbicaciones.adapter = adapter
        binding = FragmentListaUbicacionesBinding.bind(view).apply{
        recyclerViewUbicaciones.adapter = adapter
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        //Hacemos la llamada a la función.
        if (adapter.itemCount == 0){
            loadItems()
        }

    }//Fin del onViewCreated

    private fun loadItems() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            binding.progress.visibility = View.VISIBLE //Cuando se cargue, se mostrará visible.
            val ubicaciones = withContext(Dispatchers.IO){ UbicacionesProvider.getUbicaciones()} //El dispatchers lo que hace es cambiar el hilo.
            adapter.ubicaciones = ubicaciones
            adapter.notifyDataSetChanged()
            binding.progress.visibility = GONE //Cuando se termine de cargar, se ocultará.
        }
    }

}//Fin de la clase.

    