package com.example.searchjobapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.searchjobapp.MainActivity
import com.example.searchjobapp.R
import com.example.searchjobapp.databinding.FragmentJobDetailsViewsBinding
import com.example.searchjobapp.models.Job
import com.example.searchjobapp.models.JobToSave
import com.example.searchjobapp.viewmodel.RemoteJobViewModel
import com.google.android.material.snackbar.Snackbar

class JobDetailsViewsFragment : Fragment(R.layout.fragment_job_details_views) {

    private var mBinding:FragmentJobDetailsViewsBinding? = null
    private val binding get() = mBinding!!
    private lateinit var currentJob: Job
    private val args: JobDetailsViewsFragmentArgs by navArgs()
    private lateinit var viewModel:RemoteJobViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentJobDetailsViewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentJob = args.job!!

        viewModel = (activity as MainActivity).viewModel

        setUpWebview()
        binding.fabAddFavorite.setOnClickListener {
            addFavJob(view)
        }
    }

    private fun addFavJob(view: View) {
        val favJob = JobToSave(
            0,
            currentJob.candidateRequiredLocation,
            currentJob.category,
            currentJob.companyLogoUrl,
            currentJob.companyName,
            currentJob.description,
            currentJob.id,
            currentJob.jobType,
            currentJob.publicationDate,
            currentJob.salary,
            currentJob.title,
            currentJob.url
        )
        viewModel.addFavJob(favJob)
        Snackbar.make(view,R.string.Job_Saved_Successfully,Snackbar.LENGTH_LONG).show()
    }

    private fun setUpWebview() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            currentJob.url?.let { loadUrl(it) }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}