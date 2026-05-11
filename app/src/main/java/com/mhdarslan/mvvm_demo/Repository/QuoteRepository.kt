package com.mhdarslan.mvvm_demo.Repository

import androidx.lifecycle.LiveData
import com.mhdarslan.mvvm_demo.DAO.QuoteDao
import com.mhdarslan.mvvm_demo.Models.Quote

class QuoteRepository(private val quoteDao: QuoteDao) {

    fun getQuotes(): LiveData<List<Quote>>{
        return quoteDao.getQuotes()
    }

    suspend fun insertQuote(quote: Quote){
        quoteDao.insertQuote(quote)
    }
}