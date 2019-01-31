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

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codelabs.paging.model.Repo

/**
 * Adapter for the list of repositories.
 */
class ReposAdapter : PagedListAdapter<Repo, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 1){//progress
            return ProgressViewHolder.create(parent)
        }
        else{
            return RepoViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            if (getItemViewType(position) == 1)
                (holder as ProgressViewHolder).bind(isLoading)
            else
                (holder as RepoViewHolder).bind(repoItem)
        }
    }

    companion object {
        private var isLoading = true

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                    oldItem.fullName == newItem.fullName

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                    oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            1;
        } else {
            0;
        }
    }


    fun setIsLoading(loading : Boolean) {
        isLoading = loading
        notifyItemChanged(super.getItemCount())
    }
}
