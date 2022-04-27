package com.nycschools.data.api

import com.nycschools.data.model.SatScore
import com.nycschools.data.model.SchoolList


interface ApiHelper {

    suspend fun getSchoolList(): List<SchoolList>
    suspend fun getSatScoreList():List<SatScore>
}