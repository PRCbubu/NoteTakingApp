package com.example.newnotetakingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newnotetakingapp.MainActivity
import com.example.newnotetakingapp.R
import com.example.newnotetakingapp.adapter.NoteAdapter
import com.example.newnotetakingapp.databinding.FragmentUpdateNoteBinding
import com.example.newnotetakingapp.model.NoteEntity
import com.example.newnotetakingapp.viewmodel.NoteViewModel


class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NoteViewModel

    private lateinit var currentNote: NoteEntity
    private val args: UpdateNoteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        currentNote = args.note!!

        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)

        binding.fabDone.setOnClickListener{
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()

            if(title.isNotEmpty())
            {
                val note = NoteEntity(currentNote.id, title, body)
                viewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            else
            {
                Toast.makeText(context, "Please enter note title", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNote()
    {
        AlertDialog.Builder(activity as MainActivity).apply {
            setTitle("Delete Note")
            setMessage("You want to delete this note?")
            setPositiveButton("Delete"){_,_ ->
                viewModel.deleteNote(currentNote)

                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}