package com.mhdarslan.mvvm_demo.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdarslan.mvvm_demo.Models.Quote
import com.mhdarslan.mvvm_demo.Repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository): ViewModel() {

    fun getQuotes(): LiveData<List<Quote>>{
        return quoteRepository.getQuotes()
    }

    fun insertQuote(quote: Quote){
        viewModelScope.launch(Dispatchers.IO){ // background thread
            quoteRepository.insertQuote(quote)
        }
    }
}