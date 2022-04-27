package com.nycschools.ui.satscore

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nycschools.data.api.ApiHelperImpl
import com.nycschools.data.api.RetrofitBuilder
import com.nycschools.data.model.SatScore
import com.nycschools.databinding.ActivitySatscoreBinding
import com.nycschools.shared.base.BaseActivity
import com.nycschools.shared.viewmodel.ViewModelFactory
import com.nycschools.utils.Status

class SatScoreListActivity : BaseActivity() {

    private lateinit var mbinding: ActivitySatscoreBinding
    private lateinit var satScoreViewModel: SatScoreViewModel
    private lateinit var adapter : SatScoreListAdapter
    private val schoolSatScoreList = ArrayList<SatScore>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mbinding = ActivitySatscoreBinding.inflate(layoutInflater)
        setContentView(mbinding.root)

        setupViewModel()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupViewModel() {
        satScoreViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))
        ).get(SatScoreViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter =  SatScoreListAdapter(schoolSatScoreList)
        mbinding.schoolSatscore.layoutManager = LinearLayoutManager(applicationContext)
        mbinding.schoolSatscore.adapter = adapter
    }

    private fun setupObservers() {
        satScoreViewModel.getSchoolSatScoreList().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let { schoolSatScoreList: List<SatScore> ->
                        renderSatScoreList(schoolSatScoreList)
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

    private fun renderSatScoreList(satScoreList: List<SatScore>) {
        val dbn = intent.extras?.getString("DBN","")
        val selectDbnScore = satScoreList.filter { it.dbn == dbn }
        schoolSatScoreList.addAll(selectDbnScore)
        adapter.notifyDataSetChanged()
    }
}