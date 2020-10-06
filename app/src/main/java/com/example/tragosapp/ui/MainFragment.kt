package com.example.tragosapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tragosapp.DataSource
import com.example.tragosapp.Drink
import com.example.tragosapp.R
import com.example.tragosapp.domain.RepoImpl
import com.example.tragosapp.ui.viewmodel.MainViewModel
import com.example.tragosapp.ui.viewmodel.VMFactory
import com.example.tragosapp.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(),MainAdapter.OnTragoClickListener {

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Failure -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    rv_tragos.adapter = MainAdapter(requireContext(), result.data, this)
                }
            }
        })
    }

    private fun setupSearchView(){
        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setTrago(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }


        })
    }

    private fun setupRecyclerView(){
        rv_tragos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    override fun onTragoClick(drink: Drink) {
        val bundle = Bundle()
        bundle.putParcelable("drink",drink)

        findNavController().navigate(R.id.action_mainFragment_to_tragosDetalleFragment, bundle)
    }
}