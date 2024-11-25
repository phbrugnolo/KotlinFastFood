package com.example.fastfooda1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fastfooda1.database.DAO.CustomerDao
import com.example.fastfooda1.database.DAO.ProductDao
import com.example.fastfooda1.database.DAO.SaleDao
import com.example.fastfooda1.models.Customer
import com.example.fastfooda1.models.Product
import com.example.fastfooda1.models.Sale

@Database(entities = [Product::class, Customer::class, Sale::class], version = 4, exportSchema = true)
abstract class FastFoodDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao
    abstract fun saleDao(): SaleDao

    companion object {
        @Volatile
        private var Instance: FastFoodDatabase? = null

        fun getDatabase(context: Context): FastFoodDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FastFoodDatabase::class.java,
                    "fast_food_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
