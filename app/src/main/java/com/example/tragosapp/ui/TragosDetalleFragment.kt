package com.example.tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.tragosapp.DataSource
import com.example.tragosapp.Drink
import com.example.tragosapp.R
import com.example.tragosapp.domain.RepoImpl
import com.example.tragosapp.ui.viewmodel.MainViewModel
import com.example.tragosapp.ui.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

class TragosDetalleFragment : Fragment() {

    private lateinit var drink: Drink
    private val viewModel by viewModels<MainViewModel>{
        VMFactory(RepoImpl(DataSource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tragos_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.imagen).into(img_trago)
        trago_tittle.text = drink.nombre
        trago_desc.text = drink.descripcion
    }
}