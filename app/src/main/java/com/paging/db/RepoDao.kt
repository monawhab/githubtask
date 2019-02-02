
package com.paging.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.paging.model.Repo

/**
 * Room data access object for accessing the [Repo] table.
 */
@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Repo>)

    // Do a similar query as the search API:
    // Look for repos that contain the query string in the name or in the description
    // and order those results descending, by the number of stars and then by name
    @Query("SELECT * FROM repos ORDER BY name COLLATE  NOCASE  ASC")// ORDER BY fullName ASC
    fun getRepos(): DataSource.Factory<Int,Repo>
}
