package com.example.whatstheweather.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PlaceInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( items: List<PlaceInfo>)

    @Query("select * from placeinfo")
    fun getItems(): LiveData<List<PlaceInfo>>


}