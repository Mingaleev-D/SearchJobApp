package com.example.searchjobapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.searchjobapp.databinding.JobLayoutAdapterBinding
import com.example.searchjobapp.fragments.MainFragmentDirections
import com.example.searchjobapp.models.Job
import com.example.searchjobapp.models.JobToSave

/**
 * @author : Mingaleev D
 * @data : 20/08/2022
 */

class FavJobAdapter constructor(
    private val itemClick: OnItemClickListener
) : RecyclerView.Adapter<FavJobAdapter.RemoteJobViewHolder>() {

    private var binding: JobLayoutAdapterBinding? = null

    class RemoteJobViewHolder(itemBinding: JobLayoutAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object :
        DiffUtil.ItemCallback<JobToSave>() {
        override fun areItemsTheSame(oldItem: JobToSave, newItem: JobToSave): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JobToSave, newItem: JobToSave): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteJobViewHolder {
        binding = JobLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RemoteJobViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RemoteJobViewHolder, position: Int) {
        val currentJob = differ.currentList[position]

        holder.itemView.apply {

            Glide.with(this)
                .load(currentJob.companyLogoUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding?.ivCompanyLogo!!)

            binding?.tvCompanyName?.text = currentJob.companyName
            binding?.tvJobLocation?.text = currentJob.candidateRequiredLocation
            binding?.tvJobTitle?.text = currentJob.title
            binding?.tvJobType?.text = currentJob.jobType

            binding?.ibDelete?.visibility = View.VISIBLE

            val dateJob = currentJob.publicationDate?.split("T")
            binding?.tvDate?.text = dateJob?.get(0)

        }.setOnClickListener { mView ->
            val tags = arrayListOf<String>()

            val job = Job(
                currentJob.candidateRequiredLocation,
                currentJob.category,
                currentJob.companyLogoUrl,
                currentJob.companyName,
                currentJob.description,
                currentJob.jobId,
                currentJob.jobType,
                currentJob.publicationDate,
                currentJob.salary,
                tags,
                currentJob.title,
                currentJob.url
            )



            val direction = MainFragmentDirections
                .actionMainFragmentToJobDetailsViewsFragment(job)
            mView.findNavController().navigate(direction)
        }

        holder.itemView.apply {
            binding?.ibDelete?.setOnClickListener {
                itemClick.onItemClick(currentJob,binding?.ibDelete!!,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnItemClickListener {
        fun onItemClick(
            job:JobToSave,
            view: View,
            position: Int
        )
    }
}