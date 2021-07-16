package com.example.flickrcl.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.flickrcl.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
    ): ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap { searchText ->
        repository.getSearchResults(searchText).cachedIn(viewModelScope)
    }

    fun searchPhotos(searchText: String) {
        currentQuery.value = searchText
    }

    companion object {
        private const val DEFAULT_QUERY = "dog"
    }
}