
package com.paging.data

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.paging.api.GithubService
import com.paging.db.GithubLocalCache
import com.paging.model.RepoSearchResult

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
