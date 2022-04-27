package com.nycschools.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nycschools.R


////////////////////////////////////////
fun showAlertDialog(
    context: Context,
    title: String,
    message: String,
    btn1Title: String = "Leave",
    btn1Click: (() -> Unit)? = null,
    btn2Title: String = "Cancel",
    btn2Click: (() -> Unit)? = null
): AlertDialog {
    val dialog = AlertDialog.Builder(context, R.style.Theme_NYCSchools)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(btn1Title) { dialog, _ ->
            btn1Click?.let { it() }
            dialog.dismiss()
        }
        .setNegativeButton(btn2Title) { dialog, _ ->
            btn2Click?.let { it() }
            dialog.dismiss()
        }
        .show()

    return dialog
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@BindingAdapter("is_vertical")
fun RecyclerView.isVertical(isVertical: Boolean) {
    layoutManager = LinearLayoutManager(
        this.context,
        if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL,
        false
    )
}
