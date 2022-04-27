package com.nycschools.ui.satscore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nycschools.data.model.SatScore
import com.nycschools.data.respository.SchoolRepository
import com.nycschools.utils.Resource
import kotlinx.coroutines.launch

class SatScoreViewModel (private val schoolRepository: SchoolRepository): ViewModel() {

    private val schoolSatScoreItemList = MutableLiveData<Resource<List<SatScore>>>()
    init {
        fetchSchoolSatScoreList()
    }

    fun fetchSchoolSatScoreList(){
        viewModelScope.launch {
            schoolSatScoreItemList.postValue(Resource.loading(null))
            try {
                val response = schoolRepository.getSatScore()
                schoolSatScoreItemList.postValue(Resource.success(response))
            }catch (e:Exception){
                schoolSatScoreItemList.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getSchoolSatScoreList(): LiveData<Resource<List<SatScore>>> {
        return schoolSatScoreItemList
    }
}