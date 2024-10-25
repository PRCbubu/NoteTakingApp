package com.example.newnotetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.newnotetakingapp.model.NoteEntity
import com.example.newnotetakingapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val noteRepository: NoteRepository) : AndroidViewModel(app) {
    fun addNote(note: NoteEntity) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun deleteNote(note: NoteEntity) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun updateNote(note: NoteEntity) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    fun searchNote(query: String?) = noteRepository.searchNote(query)
}