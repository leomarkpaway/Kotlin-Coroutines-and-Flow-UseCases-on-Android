package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase2.database.StockEntity

@Database(entities = [StockEntity::class], version = 1, exportSchema = false)
abstract class StockDatabase : RoomDatabase() {

    abstract fun stockDao(): StockDao

    companion object {
        private var INSTANCE: StockDatabase? = null

        fun getInstance(context: Context): StockDatabase {
            if (INSTANCE == null) {
                synchronized(StockDatabase::class) {
                    INSTANCE = buildRoomDb(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDb(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                StockDatabase::class.java,
                "stocks.db"
            ).build()

    }

}
