package com.example.newnotetakingapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.newnotetakingapp.database.NoteDatabase
import com.example.newnotetakingapp.databinding.ActivityMainBinding
import com.example.newnotetakingapp.repository.NoteRepository
import com.example.newnotetakingapp.viewmodel.NoteViewModel
import com.example.newnotetakingapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val noteRepository = NoteRepository(NoteDatabase(this))
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}