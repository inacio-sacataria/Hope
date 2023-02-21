package com.decode.hope.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.decode.hope.data.db.HopeDatabase
import com.decode.hope.data.db.dao.NotesDao
import com.decode.hope.data.db.entities.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesRepository(private val database: HopeDatabase) {

    private  var notesDao : NotesDao
    init {
        notesDao = database.getNotesDao()
    }

   suspend fun insertNotes(note: Note) : Boolean {
        var isInserted = false

       CoroutineScope(Dispatchers.IO).launch {
           notesDao.insertWithTimeStamps(note)
           isInserted = true
       }
        return isInserted
    }

    suspend fun update(note: Note):Boolean{
        var isUpdate = false

        CoroutineScope(Dispatchers.IO).launch {
            notesDao.updateWithTimeStamps(note)
            isUpdate=true
        }
        return isUpdate
    }

    suspend fun delete(note: Note):Boolean{
        var isDelete = false

        CoroutineScope(Dispatchers.IO).launch {
            notesDao.delete(note)
            isDelete=true
        }
        return isDelete
    }

   suspend fun getAllNotes(): MutableLiveData<MutableList<Note>>{
          var notesList = MutableLiveData<MutableList<Note>>()
          var note = mutableListOf<Note>()
           CoroutineScope(Dispatchers.IO).launch {
               for (item in notesDao.getAll()) {
                   note.add(item)
                   Log.d(NotesDao.TAG, "listed ${item.title}")
               }
               notesList.postValue(note)
           }
        return notesList
    }


    companion object {
        val TAG = "mydb"
    }
}
