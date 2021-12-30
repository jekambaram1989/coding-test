package com.codingtest.ul

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.codingtest.ul.di.DaggerTestAppComponent
import com.codingtest.ul.di.TestAppModule
import com.codingtest.ul.repository.UniversityRepository
import com.codingtest.ul.ui.viewmodel.UniversityViewModel
import com.codingtest.ul.util.NetworkState
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

    @Mock
    private lateinit var mockContext: Context

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
                .testAppModule(TestAppModule(mockContext))
                .build()
        component.inject(this)
        viewModel = UniversityViewModel(universityRepository)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            val data = Any()
            doReturn(NetworkState.success(data)).`when`(universityRepository)
                .getUniversityListNetwork()
            viewModel.getUniversityListNetwork().observeForever(observer)
            verify(universityRepository).getUniversityListNetwork()
            verify(observer, times(1)).onChanged(NetworkState.success(data))
            viewModel.getUniversityListNetwork().removeObserver(observer)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Network error"
            doThrow(RuntimeException(errorMessage)).`when`(universityRepository)
                .getUniversityListNetwork()
            viewModel.getUniversityListNetwork().observeForever(observer)
            verify(universityRepository).getUniversityListNetwork()
            verify(observer, times(1)).onChanged(NetworkState.error(errorMessage))
            viewModel.getUniversityListNetwork().removeObserver(observer)
        }
    }
}