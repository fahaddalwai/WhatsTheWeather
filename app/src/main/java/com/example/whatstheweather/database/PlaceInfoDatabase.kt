package com.example.whatstheweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [PlaceInfo::class], version = 9)
abstract class PlaceInfoDatabase : RoomDatabase() {

    abstract val placeInfoDao: PlaceInfoDao

    companion object {           //companion object is used to  access the methods for creating or getting the database without instantiating the class

        @Volatile                  //value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory
        private lateinit var INSTANCE: PlaceInfoDatabase         //keep a reference to the database, when one has been created

        fun getInstance(context: Context): PlaceInfoDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        PlaceInfoDatabase::class.java,
                        "videos"
                    ).build()
                }
            }
            return INSTANCE
        }
        }
    }
