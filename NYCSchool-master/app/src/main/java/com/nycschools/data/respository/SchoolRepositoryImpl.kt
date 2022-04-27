package com.nycschools.data.respository

import com.nycschools.data.api.ApiHelper

class SchoolRepositoryImpl(private val apiHelper: ApiHelper):SchoolRepository {
    override suspend fun getSchoolList() =  apiHelper.getSchoolList()
    override suspend fun getSatScore() = apiHelper.getSatScoreList()
}