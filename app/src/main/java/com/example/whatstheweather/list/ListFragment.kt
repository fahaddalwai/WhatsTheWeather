package com.example.whatstheweather.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.whatstheweather.R
import com.example.whatstheweather.database.PlaceInfoDatabase
import com.example.whatstheweather.databinding.FragmentListBinding
import com.example.whatstheweather.network.ApiService
import com.example.whatstheweather.network.CLIENT_ID
import com.example.whatstheweather.network.ResponseApi
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding:FragmentListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list,
            container,
            false)

        val application = requireNotNull(this.activity).application
        val dataSource = PlaceInfoDatabase.getInstance(application).placeInfoDao

        val viewModelFactory = ListViewModelFactory(dataSource, application)
        viewModel =
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ListViewModel::class.java)      //define instance of viewmodel using provider


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter=ItemListAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer{
            it?.let{
                adapter.submitList(it)
            }
        })



        return binding.root
    }


}