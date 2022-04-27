package com.nycschools.data.respository

import com.nycschools.data.model.SatScore
import com.nycschools.data.model.SchoolList

interface SchoolRepository {
    suspend fun getSchoolList() : List<SchoolList>
    suspend fun getSatScore():List<SatScore>
}