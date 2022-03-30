package ca.on.listech.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.on.listech.borutoapp.data.local.dao.HeroDao
import ca.on.listech.borutoapp.data.local.dao.HeroRemoteKeysDao
import ca.on.listech.borutoapp.domain.model.Hero
import ca.on.listech.borutoapp.domain.model.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 2)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDB: RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeysDao
}