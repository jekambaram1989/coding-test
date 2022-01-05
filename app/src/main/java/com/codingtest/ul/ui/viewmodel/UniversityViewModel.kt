package com.codingtest.ul.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingtest.ul.repository.UniversityRepository
import com.codingtest.ul.util.ApiException
import com.codingtest.ul.util.NetworkState
import com.codingtest.ul.util.NoInternetException
import kotlinx.coroutines.launch
import javax.inject.Inject

class UniversityViewModel @Inject constructor(private val repository: UniversityRepository) :
    ViewModel() {

    private val universities = MutableLiveData<NetworkState>()


    fun getUniversities(): LiveData<NetworkState> {
        viewModelScope.launch {
            val localList = repository.getLocalUniversities()
            if (localList.isEmpty()) {
                try {
                    val networkList = repository.getNetworkUniversities()
                    repository.deleteAll()
                    repository.insertUniversities(networkList)
                    universities.postValue(NetworkState.success(networkList))
                } catch (e: ApiException) {
                    universities.postValue(NetworkState.error("Server error"))
                } catch (e: NoInternetException) {
                    universities.postValue(NetworkState.error("Network error"))
                } catch (e: Exception) {
                    universities.postValue(NetworkState.error("Unknown error"))
                }
            } else {
                universities.postValue(NetworkState.success(localList))
            }
        }
        return universities
    }
}