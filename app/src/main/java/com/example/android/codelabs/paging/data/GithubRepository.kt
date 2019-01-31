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

package com.example.android.codelabs.paging.data

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.codelabs.paging.api.GithubService
import com.example.android.codelabs.paging.db.GithubLocalCache
import com.example.android.codelabs.paging.model.RepoSearchResult

/**
 * Repository class that works with local and remote data sources.
 */
class GithubRepository(
    private val service: GithubService,
    private val cache: GithubLocalCache
) {
    fun loadRepos(): RepoSearchResult {

        // Get data source factory from the local cache
        val dataSourceFactory = cache.getRepos()

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = RepoBoundaryCallback(service, cache)
        val networkErrors = boundaryCallback.networkErrors

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
                .setPageSize(DATABASE_PAGE_SIZE)//.setPrefetchDistance(DATABASE_PAGE_SIZE-2)
                .build()
        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return RepoSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 15
        private const val INITIAL_LOAD_SIZE_HINT = DATABASE_PAGE_SIZE*2
    }
}
