package com.codingtest.ul.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingtest.ul.R
import com.codingtest.ul.network.response.University

class UniversityAdapter :
    RecyclerView.Adapter<UniversityAdapter.ViewHolder>() {

    private var universityList = ArrayList<University>()

    fun updateList(universityList: List<University>) {
        this.universityList = universityList as ArrayList<University>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(universityList[position])
    }

    override fun getItemCount(): Int {
        return universityList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(university: University) {
            val country = itemView.findViewById(R.id.item1) as TextView
            val name = itemView.findViewById(R.id.item2) as TextView
            country.text = "Country: ${university.country}"
            name.text = "University: ${university.name}"
        }
    }
}