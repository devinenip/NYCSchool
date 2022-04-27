package com.nycschools.data.api

import com.nycschools.data.model.SatScore
import com.nycschools.data.model.SchoolList
import retrofit2.http.GET

interface ApiService {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchoolDirectoryList(): List<SchoolList>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSatScores(): List<SatScore>

}