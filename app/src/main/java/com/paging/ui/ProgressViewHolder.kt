
package com.paging.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.paging.R
import com.paging.model.Repo

/**
 * View Holder for a [Repo] RecyclerView list item.
 */
class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val progressbar: ProgressBar = view.findViewById(R.id.progressbar)

    companion object {
        fun create(parent: ViewGroup): ProgressViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.progress_item, parent, false)
            return ProgressViewHolder(view)
        }
    }
    fun bind(isLoading: Boolean?) {
        progressbar.visibility = if(isLoading == true) View.VISIBLE else View.INVISIBLE
    }

}
