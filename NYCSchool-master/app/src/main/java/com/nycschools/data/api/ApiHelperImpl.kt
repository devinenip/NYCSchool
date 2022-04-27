package com.nycschools.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getSchoolList() = apiService.getSchoolDirectoryList()

    override suspend fun getSatScoreList() = apiService.getSatScores()
}