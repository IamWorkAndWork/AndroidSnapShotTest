package com.example.androidsnapshottest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            delay(2000)
            try {
                val profile = fetchProfileData()
                _viewState.value = ViewState.Success(profile)
            } catch (e: Exception) {
                _viewState.value = ViewState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private fun fetchProfileData(): ProfileModel {
        return ProfileModel(
            name = "John Doe",
            country = "United States",
            profileImageUrl = null
        )
    }
}

data class ProfileModel(
    val name: String,
    val country: String,
    val profileImageUrl: String? = null,
)

sealed interface ViewState {
    object Loading : ViewState
    data class Success(val profile: ProfileModel) : ViewState
    data class Error(val message: String) : ViewState
}