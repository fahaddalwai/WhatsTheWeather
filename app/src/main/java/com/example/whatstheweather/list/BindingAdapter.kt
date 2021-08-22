package com.example.whatstheweather.list

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.whatstheweather.domain.PlacesModel


@BindingAdapter("Location")
fun TextView.setLocation(item: PlacesModel?) {
    item?.let {
        text = item.Name
        Log.i("name",text as String)
    }
}


@BindingAdapter("Temp")
fun TextView.setTemp(item: PlacesModel?) {
    item?.let {
        text= item.temp.toString()
        Log.i("food",text as String)
    }
}

@BindingAdapter("FeelsLike")
fun TextView.setFeelsLike(item: PlacesModel?) {
    item?.let {
        text= item.feelsLike.toString()
        Log.i("food",text as String)
    }
}



