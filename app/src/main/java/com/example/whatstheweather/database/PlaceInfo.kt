package com.example.whatstheweather.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whatstheweather.domain.PlacesModel
import com.example.whatstheweather.network.Main
import com.example.whatstheweather.network.ResponseItem

//the data to be put into the database
@Entity
data class PlaceInfo(

    @PrimaryKey(autoGenerate = false)
    var id: Long = 0L,

    @ColumnInfo(name = "Location Name")
    var Name: String ="default",

    @ColumnInfo(name = "Temperature")
    var temp: Float=0f,

    @ColumnInfo(name = "Feels Like")
    var feelsLike: Float=0f,
)

/**
 * Map DatabaseVideos to domain entities
 */
fun List<PlaceInfo>.asDomainModel(): List<PlacesModel> {
return map{
    PlacesModel(
        Name=it.Name,
        temp=it.temp,
        feelsLike=it.feelsLike
    )
}

}