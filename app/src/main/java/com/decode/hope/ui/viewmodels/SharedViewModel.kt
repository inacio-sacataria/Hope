package com.decode.hope.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decode.hope.data.db.HopeDatabase
import com.decode.hope.data.db.entities.Note
import com.decode.hope.data.repositories.NotesRepository
import kotlinx.coroutines.launch

class SharedViewModel()  : ViewModel(){


    private lateinit var notesRepository: NotesRepository
    public var _notes = MutableLiveData<MutableList<Note>>()
    public var singleNotes = MutableLiveData<MutableList<Note>>()
    var notes : MutableLiveData<MutableList<Note>> = _notes



    fun initialize(context: Context){
        notesRepository = NotesRepository(HopeDatabase.getInstance(context))
        getData()

    }
    fun saveData(note: Note){
        viewModelScope.launch {
            notesRepository.insertNotes(note)
        }
    }

    fun updateData(note: Note){
        viewModelScope.launch {
            notesRepository.update(note)
        }
    }

    fun deleteData(note: Note){
        viewModelScope.launch {
            notesRepository.delete(note)
        }
    }

    fun getData(){
        viewModelScope.launch {
         _notes =  notesRepository.getAllNotes()
        }
    }
}