package com.codingtest.ul.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtest.ul.network.response.University
import com.codingtest.ul.repository.UniversityRepository
import com.codingtest.ul.util.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UniversityViewModel @Inject constructor(private val repository: UniversityRepository) :
    ViewModel() {

    private val networkList = MutableLiveData<NetworkState>()
    private val localList = MutableLiveData<List<University>>()

    fun getUniversityListNetwork(): LiveData<NetworkState> {
        viewModelScope.launch {
            try {
                networkList.postValue(repository.getUniversityListNetwork())
            } catch (e: Exception) {
                networkList.postValue(NetworkState.error("Network error"))
            }
        }
        return networkList
    }

    fun getListFromDatabase(): LiveData<List<University>> {
        viewModelScope.launch(Dispatchers.IO) {
            localList.postValue(repository.getUniversities())
        }
        return localList
    }

    fun setListToDatabase(universityList: List<University>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            repository.insertUniversities(universityList)
        }
    }
}