package com.example.whatstheweather.list

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatstheweather.database.PlaceInfoDao
import com.example.whatstheweather.domain.PlacesModel
import com.example.whatstheweather.repository.Repository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okio.IOException

class ListViewModel(dataSource: PlaceInfoDao, application: Application) : ViewModel() {

    val repository=Repository(dataSource)

    val items=repository.itemsToDisplay


    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {

                repository.refreshList()


            } catch (networkError: IOException) {

                // Show a Toast error message and hide the progress bar.
                if(repository.itemsToDisplay.value.isNullOrEmpty()){
                    Log.i("fail",networkError.message.toString())
                }
            }
        }
    }

    init {

        refreshDataFromRepository()

    }
}