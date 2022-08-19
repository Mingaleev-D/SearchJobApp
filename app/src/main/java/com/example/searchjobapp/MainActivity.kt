package com.example.searchjobapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.searchjobapp.databinding.ActivityMainBinding
import com.example.searchjobapp.repository.RemoteJobRepository
import com.example.searchjobapp.viewmodel.RemoteJobViewModel
import com.example.searchjobapp.viewmodel.RemoteJobViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    lateinit var viewModel:RemoteJobViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title= ""

        setUpViewModel()
    }

    private fun setUpViewModel() {

        val remoteJobRepository = RemoteJobRepository()

        val viewModelProvider = RemoteJobViewModelFactory(
            application,
            remoteJobRepository
        )
        viewModel = ViewModelProvider(this,viewModelProvider)[RemoteJobViewModel::class.java]



    }
}