package ca.on.listech.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ca.on.listech.borutoapp.data.local.dao.HeroDao
import ca.on.listech.borutoapp.data.local.dao.HeroRemoteKeyDao
import ca.on.listech.borutoapp.domain.model.Hero
import ca.on.listech.borutoapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDB: RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}