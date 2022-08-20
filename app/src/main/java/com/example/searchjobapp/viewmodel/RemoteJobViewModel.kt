package com.example.searchjobapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchjobapp.db.FavoriteJobDao
import com.example.searchjobapp.models.JobToSave
import com.example.searchjobapp.repository.RemoteJobRepository
import kotlinx.coroutines.launch

/**
 * @author : Mingaleev D
 * @data : 20/08/2022
 */

class RemoteJobViewModel(
    app:Application,
    private val remoteJobRepository: RemoteJobRepository
):AndroidViewModel(app) {

    fun remoteJobResult() = remoteJobRepository.remoteJobResult()


    fun addFavJob(job:JobToSave) = viewModelScope.launch {
        remoteJobRepository.addFavoriteJob(job)
    }
    fun deleteJob(job:JobToSave) = viewModelScope.launch {
        remoteJobRepository.deleteJob(job)
    }
    fun getAllFavJobs() = remoteJobRepository.getAllFavJobs()



}