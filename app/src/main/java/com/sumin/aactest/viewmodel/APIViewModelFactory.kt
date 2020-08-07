package com.sumin.aactest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sumin.aactest.data.LocalReposirtory
import com.sumin.aactest.data.SearchRepository

class APIViewModelFactory(
    private val searchRepo : SearchRepository,
    private val localRepo : LocalReposirtory
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return APIViewModel(searchRepo, localRepo) as T
    }
}