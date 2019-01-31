/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.paging.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codelabs.paging.R
import com.example.android.codelabs.paging.model.Repo

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
