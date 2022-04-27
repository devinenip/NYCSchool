package com.nycschools.shared.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nycschools.data.api.ApiHelper
import com.nycschools.data.respository.SchoolRepositoryImpl
import com.nycschools.ui.SchoolDirectoryViewModel
import com.nycschools.ui.satscore.SatScoreViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(SchoolDirectoryViewModel::class.java) -> {
                return SchoolDirectoryViewModel(SchoolRepositoryImpl(apiHelper)) as T
            }
            modelClass.isAssignableFrom(SatScoreViewModel::class.java) -> {
                return SatScoreViewModel(SchoolRepositoryImpl(apiHelper)) as T
            }
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }

}