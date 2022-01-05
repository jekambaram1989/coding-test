package com.codingtest.ul.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingtest.ul.databinding.FragmentItemBinding
import com.codingtest.ul.network.response.University

class UniversityAdapter :
    RecyclerView.Adapter<UniversityAdapter.ViewHolder>() {

    private var universityList = ArrayList<University>()

    fun updateList(universityList: List<University>) {
        this.universityList = universityList as ArrayList<University>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(universityList[position])
    }

    override fun getItemCount(): Int {
        return universityList.size
    }

    class ViewHolder(private val binding: FragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(university: University) {
            binding.txtCountryName.text = "Country: ${university.country}"
            binding.txtUniversityName.text = "University: ${university.name}"
        }
    }
}