package com.example.newnotetakingapp.repository

import com.example.newnotetakingapp.database.NoteDatabase
import com.example.newnotetakingapp.model.NoteEntity

class NoteRepository(private val db: NoteDatabase)
{
    suspend fun insertNote(note: NoteEntity) = db.noteDao().insertNote(note)
    suspend fun deleteNote(note: NoteEntity) = db.noteDao().deleteNote(note)
    suspend fun updateNote(note: NoteEntity) = db.noteDao().updateNote(note)

    fun getAllNotes() = db.noteDao().getAllNotes()
    fun searchNote(query: String?) = db.noteDao().searchNote(query)

}