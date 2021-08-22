package com.example.whatstheweather.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.whatstheweather.database.PlaceInfoDao
import com.example.whatstheweather.database.asDomainModel
import com.example.whatstheweather.domain.PlacesModel
import com.example.whatstheweather.network.ApiService
import com.example.whatstheweather.network.CLIENT_ID
import com.example.whatstheweather.network.ResponseApi
import com.example.whatstheweather.network.ResponseApi.retrofitService
import com.example.whatstheweather.network.asDatabasemodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val dao: PlaceInfoDao) {

    val itemsToDisplay: LiveData<List<PlacesModel>> = Transformations.map(dao.getItems()) {
        it.asDomainModel()
    }

    val value=dao.getItems()

    /**
     * Refresh the videos stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */
    suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val responseItems = retrofitService.getResponse(38,35,15, CLIENT_ID)
            dao.insertAll(responseItems.asDatabasemodel())

        }
    }

}