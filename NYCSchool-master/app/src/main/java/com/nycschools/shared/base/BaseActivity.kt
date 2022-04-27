package com.nycschools.shared.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.nycschools.R
import com.nycschools.utils.ConnectivityChangeReceiver
import com.nycschools.utils.showAlertDialog
import com.nycschools.utils.showToast


abstract class BaseActivity : AppCompatActivity(), IBaseView {

    lateinit var view: View
    lateinit var parentViewGroup: ViewGroup
    private val connectivityChangeReceiver = ConnectivityChangeReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity)
        parentViewGroup = findViewById(R.id.activity_parent_container)
        view = layoutInflater.inflate(R.layout.progress_bar, null)
        parentViewGroup.addView(view)
        view.visibility = View.GONE
        val intentFilter = IntentFilter()

        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityChangeReceiver, intentFilter)
    }

    override fun showProgressBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        view.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        if (view.visibility == View.VISIBLE) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            view.visibility = View.GONE
        }
    }

    override fun showError(s: String) {
        showToast(this, s)
    }

    override fun showErrorDialog(array: Array<String>) {
        showAlertDialog(this, array[0], array[1])
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityChangeReceiver)
    }
}