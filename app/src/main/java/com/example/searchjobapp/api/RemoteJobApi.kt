package com.example.searchjobapp.api

import com.example.searchjobapp.models.RemoteJob
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author : Mingaleev D
 * @data : 20/08/2022
 */

interface RemoteJobApi {

    @GET("remote-jobs")
    fun getRemoteJob(): Call<RemoteJob>

    @GET("remote-jobs")
    fun searchRemoteJob(@Query("search") query: String?): Call<RemoteJob>
}