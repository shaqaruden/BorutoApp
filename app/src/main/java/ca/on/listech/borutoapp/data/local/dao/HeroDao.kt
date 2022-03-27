package ca.on.listech.borutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ca.on.listech.borutoapp.domain.model.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero_table ORDER BY ID ASC")
    fun getHeros(): PagingSource<Int,Hero>

    @Query("Select * FROM hero_table WHERE id = :hero_id")
    fun showHero(hero_id: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllHeroes()
}