package com.example.searchjobapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.searchjobapp.repository.RemoteJobRepository

/**
 * @author : Mingaleev D
 * @data : 20/08/2022
 */

class RemoteJobViewModelFactory(
    val app: Application,
    private val remoteJobRepository: RemoteJobRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RemoteJobViewModel(app,remoteJobRepository) as T
    }
}