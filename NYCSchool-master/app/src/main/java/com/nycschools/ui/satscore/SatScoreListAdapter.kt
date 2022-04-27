package com.nycschools.ui.satscore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nycschools.R
import com.nycschools.data.model.SatScore

class SatScoreListAdapter (private val dataSet: ArrayList<SatScore>) :
    RecyclerView.Adapter<SatScoreListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val schoolName: TextView
        val math:TextView
        val reading:TextView
        val writing:TextView

        init {
            // Define  listener for the ViewHolder's View.
            schoolName = view.findViewById(R.id.school_name)
            math = view.findViewById(R.id.math)
            reading = view.findViewById(R.id.reading)
            writing = view.findViewById(R.id.writing)
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_sat_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.schoolName.text = dataSet[position].school_name
        viewHolder.math.text = "Math Score:${dataSet[position].sat_math_avg_score}"
        viewHolder.reading.text = "Reading Score:${dataSet[position].sat_critical_reading_avg_score}"
        viewHolder.writing.text = "Writing Score:${dataSet[position].sat_writing_avg_score}"
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}