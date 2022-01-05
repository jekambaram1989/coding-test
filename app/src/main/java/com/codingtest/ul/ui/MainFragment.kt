package com.codingtest.ul.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingtest.ul.databinding.FragmentMainBinding
import com.codingtest.ul.network.response.University
import com.codingtest.ul.ui.adapter.UniversityAdapter
import com.codingtest.ul.ui.viewmodel.UniversityViewModel
import com.codingtest.ul.util.Status
import com.codingtest.ul.util.showSnackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainFragment : Fragment() {

    @Inject
    lateinit var viewModel: UniversityViewModel
    private val adapter = UniversityAdapter()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        binding.recyclerview.layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.adapter = adapter
        loadUniversities()
        return binding.root
    }

    private fun loadUniversities() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getUniversities().observe(viewLifecycleOwner, {
            binding.progressBar.visibility = View.GONE
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.updateList(it.data as List<University>)
                }
                Status.ERROR -> {
                    binding.fab.showSnackbar(it.data as String)
                }
            }
        })
    }
}