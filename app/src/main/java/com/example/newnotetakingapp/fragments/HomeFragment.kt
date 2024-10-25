package com.example.newnotetakingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newnotetakingapp.MainActivity
import com.example.newnotetakingapp.R
import com.example.newnotetakingapp.adapter.NoteAdapter
import com.example.newnotetakingapp.databinding.FragmentHomeBinding
import com.example.newnotetakingapp.model.NoteEntity
import com.example.newnotetakingapp.viewmodel.NoteViewModel

class HomeFragment : Fragment(R.layout.fragment_home), SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesAdapter: NoteAdapter
    private lateinit var viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel = (activity as MainActivity).viewModel

        setUpRecyclerView()

        binding.fabAddNote.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)

        }
    }

    private fun updateUI(note: List<NoteEntity>?) {
        if(note.isNotEmpty())
        {
            binding.cardView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
        else
        {
            binding.cardView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }
    }

    private fun setUpRecyclerView() {
        notesAdapter = NoteAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = notesAdapter
        }
        activity?.let {
            viewModel.getAllNotes().observe(viewLifecycleOwner, { note -> notesAdapter.differ.submitList(note)
                updateUI(note)
        }
    }

}