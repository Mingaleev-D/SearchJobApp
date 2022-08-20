package com.example.searchjobapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchjobapp.MainActivity
import com.example.searchjobapp.R
import com.example.searchjobapp.adapter.FavJobAdapter
import com.example.searchjobapp.databinding.FragmentSavedJobBinding
import com.example.searchjobapp.models.JobToSave
import com.example.searchjobapp.viewmodel.RemoteJobViewModel


class SavedJobFragment : Fragment(R.layout.fragment_saved_job) {
    private var mBinding: FragmentSavedJobBinding? = null
    private val binding get() = mBinding!!
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var favAdapter: FavJobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSavedJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        favAdapter = FavJobAdapter()
        binding.rvJobsSaved.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(object :
                DividerItemDecoration(activity, LinearLayoutManager.VERTICAL) {})
            adapter = favAdapter
        }
        viewModel.getAllFavJobs().observe(viewLifecycleOwner, { favJob ->
            favAdapter.differ.submitList(favJob)
            updateUI(favJob)
        })
    }

    private fun updateUI(job: List<JobToSave>) {
        if (job.isNotEmpty()) {
            binding.rvJobsSaved.visibility = View.VISIBLE
            binding.cardNoAvailable.visibility = View.GONE
        }else {
            binding.rvJobsSaved.visibility = View.GONE
            binding.cardNoAvailable.visibility = View.VISIBLE
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}