package ca.on.listech.borutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.on.listech.borutoapp.domain.model.HeroRemoteKeys

@Dao
interface HeroRemoteKeysDao {

    @Query("SELECT * FROM hero_remote_keys_table WHERE id = :heroID")
    suspend fun getRemoteKey(heroID: Int): HeroRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKeys: List<HeroRemoteKeys>)

    @Query("DELETE FROM hero_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}