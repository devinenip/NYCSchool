package com.nycschools.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nycschools.data.model.SchoolList
import com.nycschools.data.respository.SchoolRepository
import com.nycschools.utils.Resource
import kotlinx.coroutines.launch

class SchoolDirectoryViewModel(private val schoolRepository: SchoolRepository):ViewModel() {

    private val schoolDirectoryItemList = MutableLiveData<Resource<List<SchoolList>>>()
    init {
        fetchSchoolDirectoryList()
    }

    fun fetchSchoolDirectoryList(){
        viewModelScope.launch {
            schoolDirectoryItemList.postValue(Resource.loading(null))
            try {
                val response = schoolRepository.getSchoolList()
                schoolDirectoryItemList.postValue(Resource.success(response))
            }catch (e:Exception){
                schoolDirectoryItemList.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getSchoolDirectoryList(): LiveData<Resource<List<SchoolList>>> {
        return schoolDirectoryItemList
    }
}