package com.mhdarslan.mvvm_demo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mhdarslan.mvvm_demo.DbHelper.QuoteDatabase
import com.mhdarslan.mvvm_demo.Models.Quote
import com.mhdarslan.mvvm_demo.Repository.QuoteRepository
import com.mhdarslan.mvvm_demo.ViewModels.MainViewModel
import com.mhdarslan.mvvm_demo.ViewModels.MainViewModelFactory
import com.mhdarslan.mvvm_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /*
        Here we create first
        1. Dao instance pass it to repository
        2. repository instance pass it to factory
        3. viewModel with factory
         */
        val dao = QuoteDatabase.getDatabase(applicationContext).quoteDao()
        val repository = QuoteRepository(dao)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.getQuotes().observe(this, Observer{
            binding.quotes = it.toString()
        })

        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0, "This is testing", "Testing")
            mainViewModel.insertQuote(quote)
        }
    }
}