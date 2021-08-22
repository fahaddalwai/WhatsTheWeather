package com.example.whatstheweather.network

import androidx.lifecycle.Transformations.map
import com.example.whatstheweather.database.PlaceInfo
import com.squareup.moshi.Json

//to parse and get the response from server

data class Response(
    val list:List<ResponseItem>
)
data class ResponseItem(
    val id: Long,
    val name:String,
    val main: Main
)

data class Main(
    val temp:Float,
    @Json(name = "feels_like") val feelsLike:Float
)


/**
 * Convert Network results to database objects
 */
fun Response.asDatabasemodel():List<PlaceInfo>{
    return list.map{
        PlaceInfo(
            id=it.id,
            Name=it.name,
            temp=it.main.temp,
            feelsLike=it.main.feelsLike
        )
    }

}
