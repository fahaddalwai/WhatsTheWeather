package com.example.whatstheweather.list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.whatstheweather.databinding.ListItemBinding
import com.example.whatstheweather.domain.PlacesModel

class ItemListAdapter : ListAdapter<PlacesModel, ItemListAdapter.ViewHolder>(PlacesModelDiffCallback()) {

    class ViewHolder private constructor(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: PlacesModel) {
            binding.placesModel = item
            Log.i("binging", item.toString())
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val placesModel=getItem(position)!!
        holder.bind(placesModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


}




class PlacesModelDiffCallback : DiffUtil.ItemCallback<PlacesModel>() {

    override fun areItemsTheSame(oldItem: PlacesModel, newItem: PlacesModel): Boolean {
        return oldItem.Name == newItem.Name
    }

    override fun areContentsTheSame(oldItem: PlacesModel, newItem: PlacesModel): Boolean {
        return oldItem == newItem
    }

}