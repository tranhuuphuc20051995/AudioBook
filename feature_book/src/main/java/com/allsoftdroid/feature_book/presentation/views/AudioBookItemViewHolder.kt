package com.allsoftdroid.feature_book.presentation.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allsoftdroid.feature_book.databinding.ArchiveBookItemBinding
import com.allsoftdroid.feature_book.domain.model.AudioBookDomainModel

/**
 * ViewHolder Class for binding the data to the Layout and constructing layout for the display
 */
class AudioBookItemViewHolder private constructor(private val binding : ArchiveBookItemBinding) : RecyclerView.ViewHolder(binding.root){

    // bind the data to the view
    fun bind(item: AudioBookDomainModel){
        binding.book = item
        binding.executePendingBindings()
    }

    //construct the viewholder
    companion object{
        fun from(parent: ViewGroup) : AudioBookItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ArchiveBookItemBinding.inflate(layoutInflater,parent,false)

            return AudioBookItemViewHolder(binding)
        }
    }
}