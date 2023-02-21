package com.decode.hope.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.decode.hope.data.db.dao.NotesDao
import com.decode.hope.data.db.entities.Note
import java.time.Instant

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class HopeDatabase : RoomDatabase() {

    abstract fun getNotesDao() : NotesDao

    companion object {

        @Volatile
        private var INSTANCE : HopeDatabase?= null

        fun getInstance(context: Context) : HopeDatabase {
            synchronized(this){
                 var instance = INSTANCE

                 if (instance == null){

                    instance = Room.databaseBuilder(
                                context,
                                HopeDatabase::class.java,
                                "HopeDatabase"
                            )
                                .fallbackToDestructiveMigration()
                                .build()
                        }
                return instance
            }
        }
    }
}