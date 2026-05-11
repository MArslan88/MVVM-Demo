package com.mhdarslan.mvvm_demo.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mhdarslan.mvvm_demo.Models.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * from quote")
    fun getQuotes() : LiveData<List<Quote>>

    @Insert
    suspend fun insertQuote(quote: Quote)
}