package com.nycschools.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nycschools.R
import com.nycschools.data.model.SchoolList
import com.nycschools.ui.satscore.SatScoreListActivity

class SchoolDirectoryListAdapter(private val dataSet: ArrayList<SchoolList>,
private val context:Activity) :
    RecyclerView.Adapter<SchoolDirectoryListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val schoolName: TextView

        init {
            // Define  listener for the ViewHolder's View.
            schoolName = view.findViewById(R.id.school_name)
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_school_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.schoolName.text = dataSet[position].school_name
        viewHolder.schoolName.setOnClickListener {
            val intent = Intent(context,SatScoreListActivity::class.java)
            intent.putExtra("DBN",dataSet[position].dbn)
            context.startActivity(intent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}