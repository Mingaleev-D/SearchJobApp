package com.example.searchjobapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.searchjobapp.repository.RemoteJobRepository

/**
 * @author : Mingaleev D
 * @data : 20/08/2022
 */

class RemoteJobViewModel(
    app:Application,
    private val remoteJobRepository: RemoteJobRepository
):AndroidViewModel(app) {

    fun remoteJobResult() = remoteJobRepository.remoteJobResult()



}