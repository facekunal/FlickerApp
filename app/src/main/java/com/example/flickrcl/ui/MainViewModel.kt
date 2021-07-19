package com.example.flickrcl.ui

import androidx.hilt.Assisted
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.flickrcl.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    @Assisted state : SavedStateHandle
    ): ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photos = currentQuery.switchMap { searchText ->
        repository.getSearchResults(searchText).cachedIn(viewModelScope)
    }

    fun searchPhotos(searchText: String) {
        currentQuery.value = searchText
    }

    companion object {
        // default query if user hasn't entered and search query
        private const val DEFAULT_QUERY = "sky"
        private const val CURRENT_QUERY = "current_query"
    }
}