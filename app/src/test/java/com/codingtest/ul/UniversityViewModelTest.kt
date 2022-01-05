package com.codingtest.ul

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.codingtest.ul.di.DaggerTestAppComponent
import com.codingtest.ul.di.TestAppModule
import com.codingtest.ul.network.response.University
import com.codingtest.ul.repository.UniversityRepository
import com.codingtest.ul.ui.viewmodel.UniversityViewModel
import com.codingtest.ul.util.NetworkState
import com.codingtest.ul.util.Status
import com.codingtest.util.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.times
import javax.inject.Inject


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UniversityViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Inject
    lateinit var universityRepository: UniversityRepository

    @Inject
    lateinit var viewModel: UniversityViewModel

    @Mock
    private lateinit var observer: Observer<NetworkState>

    @Before
    fun setUp() {
        val component =
            DaggerTestAppComponent
                .builder()
                .testAppModule(TestAppModule())
                .build()
        component.inject(this)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(ArrayList<University>()).`when`(universityRepository).getNetworkUniversities()
            doReturn(ArrayList<University>()).`when`(universityRepository).getLocalUniversities()
            viewModel.getUniversities().observeForever(observer)
            verify(universityRepository).getNetworkUniversities()
            verify(observer, times(1)).onChanged(NetworkState(Status.SUCCESS,
                ArrayList<University>()))
            viewModel.getUniversities().removeObserver(observer)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Unknown error"
            doReturn(ArrayList<University>()).`when`(universityRepository).getLocalUniversities()
            doThrow(RuntimeException(errorMessage)).`when`(universityRepository)
                .getNetworkUniversities()
            viewModel.getUniversities().observeForever(observer)
            verify(universityRepository).getNetworkUniversities()
            verify(observer, times(1)).onChanged(NetworkState(Status.ERROR, errorMessage))
            viewModel.getUniversities().removeObserver(observer)
        }
    }
}