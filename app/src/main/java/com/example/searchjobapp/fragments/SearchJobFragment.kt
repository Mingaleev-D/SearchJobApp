package com.example.searchjobapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchjobapp.MainActivity
import com.example.searchjobapp.R
import com.example.searchjobapp.adapter.RemoteJobAdapter
import com.example.searchjobapp.databinding.FragmentSearchJobBinding
import com.example.searchjobapp.models.Job
import com.example.searchjobapp.viewmodel.RemoteJobViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchJobFragment : Fragment(R.layout.fragment_search_job) {
    private var mBinding: FragmentSearchJobBinding? = null
    private val binding get() = mBinding!!
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var searchJobAdapter: RemoteJobAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSearchJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        searchJob()
        setUpRecyclerView()

    }

    private fun searchJob() {
        var job:kotlinx.coroutines.Job? = null
        binding.etSearch.addTextChangedListener {text ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                text?.let {
                    if (text.toString().isNotEmpty()){
                        viewModel.searchRemoteJob(text.toString())
                    }
                }
            }
        }
    }
    private fun setUpRecyclerView(){
        searchJobAdapter = RemoteJobAdapter()
        binding.rvSearchJobs.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = searchJobAdapter
        }
        viewModel.searchResult().observe(viewLifecycleOwner, {remoteJobs ->
            searchJobAdapter.differ.submitList(remoteJobs?.jobs)
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }


}