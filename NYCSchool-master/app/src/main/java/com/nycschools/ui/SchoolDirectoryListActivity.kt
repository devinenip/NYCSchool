package com.nycschools.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nycschools.data.api.ApiHelperImpl
import com.nycschools.data.api.RetrofitBuilder
import com.nycschools.data.model.SchoolList
import com.nycschools.databinding.ActivityMainBinding
import com.nycschools.shared.base.BaseActivity
import com.nycschools.shared.viewmodel.ViewModelFactory
import com.nycschools.utils.Status

class SchoolDirectoryListActivity : BaseActivity() {

    private lateinit var mbinding:ActivityMainBinding
    private lateinit var schoolDirectoryViewModel: SchoolDirectoryViewModel
    private lateinit var adapter : SchoolDirectoryListAdapter
    private val schoolItemList = ArrayList<SchoolList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        schoolDirectoryViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(SchoolDirectoryViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter =  SchoolDirectoryListAdapter(schoolItemList,this)
        mbinding.schoolList.layoutManager = LinearLayoutManager(applicationContext)
        mbinding.schoolList.adapter = adapter
    }

    private fun setupObservers() {
        schoolDirectoryViewModel.getSchoolDirectoryList().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let { schoolList: List<SchoolList> ->
                        renderSchoolList(schoolList)
                    }
                }
                Status.LOADING -> {
                    showProgressBar()
                    Log.d("..Loading.", "...${it.status}")
                }
                else -> {
                    hideProgressBar()
                    Log.e(".Error..", "...${it.message}")
                }
            }
        }
    }

    private fun renderSchoolList(schoolList: List<SchoolList>) {
        schoolItemList.addAll(schoolList)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        if(schoolDirectoryViewModel!=null){
            schoolItemList.clear()
            schoolDirectoryViewModel.fetchSchoolDirectoryList()
        }
    }
}