package com.codingtest.ul.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingtest.ul.R
import com.codingtest.ul.network.response.University
import com.codingtest.ul.ui.adapter.UniversityAdapter
import com.codingtest.ul.ui.viewmodel.UniversityViewModel
import com.codingtest.ul.util.Status
import com.codingtest.ul.util.showSnackbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.AndroidInjection
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: UniversityViewModel
    private lateinit var recyclerView: RecyclerView
    private val universityAdapter = UniversityAdapter()
    private lateinit var fab: FloatingActionButton
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.fab)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = universityAdapter
        isDataAvailableInLocalDB()
    }

    private fun isDataAvailableInLocalDB() {
        viewModel.getListFromDatabase().observe(this, {
            if (it.isEmpty()) {
                getDataFromNetwork()
            } else {
                universityAdapter.updateList(it)
            }
        })
    }

    private fun getDataFromNetwork() {
        progressBar.visibility = View.VISIBLE
        viewModel.getUniversityListNetwork().observe(this, { networkState ->
            progressBar.visibility = View.GONE
            when (networkState.status) {
                Status.SUCCESS -> {
                    val list: List<University>? = networkState.data as? List<University>
                    list?.let {
                        universityAdapter.updateList(it)
                        viewModel.setListToDatabase(list)
                        fab.showSnackbar("Updated")
                    }
                }
                Status.ERROR -> {
                    fab.showSnackbar(networkState.data as String)
                }
            }
        })
    }
}