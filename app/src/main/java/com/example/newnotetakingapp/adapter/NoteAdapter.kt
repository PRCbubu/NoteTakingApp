package com.example.newnotetakingapp.adapter

import android.graphics.Color
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newnotetakingapp.databinding.NoteLayoutBinding
import com.example.newnotetakingapp.fragments.HomeFragmentDirections
import com.example.newnotetakingapp.model.NoteEntity
import java.util.Random

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder (val itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id && oldItem.noteBody == newItem.noteBody && oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemBinding.tvNoteTitle.text = currentNote.noteTitle
        holder.itemBinding.tvNoteBody.text = currentNote.noteBody

        val random = Random()
        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        holder.itemBinding.ibColor.setBackgroundColor(color)

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            it.findNavController().navigate(direction)
        }

    }
}