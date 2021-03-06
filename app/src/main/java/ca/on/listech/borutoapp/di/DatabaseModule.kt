package ca.on.listech.borutoapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.on.listech.borutoapp.data.local.BorutoDB
import ca.on.listech.borutoapp.util.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): BorutoDB {
        return Room.databaseBuilder(
            context,
            BorutoDB::class.java,
            BORUTO_DATABASE
        ).build()
    }
}