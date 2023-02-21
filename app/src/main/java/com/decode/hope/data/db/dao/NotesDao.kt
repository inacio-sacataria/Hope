package com.decode.hope.data.db.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.decode.hope.data.db.entities.Note
import java.sql.Timestamp

@Dao
interface NotesDao {

    @Insert
    fun insert(note: Note)
    fun insertWithTimeStamps(note: Note){
        insert(note.apply {
            this.createdAt = System.currentTimeMillis()
            this.modifiedAt = System.currentTimeMillis()
        }).apply {
            Log.d(TAG,"added")
        }

    }

    @Query("Select * FROM note")
    fun getAll() : List<Note>

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)
    fun updateWithTimeStamps(note: Note){
        update(note.apply {
            this.modifiedAt= System.currentTimeMillis()
        })
    }


    companion object{
        val TAG ="mydb"
    }

}