package com.demo.navitask.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.navitask.data.PullRepository
import com.demo.navitask.data.model.PullResponse
import com.demo.navitask.domain.use_case.PullRequestUseCase
import com.demo.navitask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PullRepository
) : ViewModel() {

    private val _pullResponse = MutableLiveData<Resource<List<PullResponse>>>()
    val pullResponse: LiveData<Resource<List<PullResponse>>> = _pullResponse

    fun loadCatFact() {
        viewModelScope.launch {
            try {
                _pullResponse.postValue(Resource.Loading())
                val response = repository.getAllPullRequests()
                _pullResponse.postValue(Resource.Success(response))
            } catch (e: HttpException) {
                _pullResponse.postValue(
                    Resource.Error(
                        e.localizedMessage ?: "An unexpected error occured"
                    )
                )
            } catch (e: IOException) {
                _pullResponse.postValue(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }
}