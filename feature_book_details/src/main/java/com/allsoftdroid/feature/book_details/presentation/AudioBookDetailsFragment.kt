package com.allsoftdroid.feature.book_details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.allsoftdroid.common.base.fragment.BaseContainerFragment
import com.allsoftdroid.feature.book_details.R
import com.allsoftdroid.feature.book_details.databinding.FragmentAudiobookDetailsBinding
import com.allsoftdroid.feature.book_details.presentation.viewModel.BookDetailsViewModel
import com.allsoftdroid.feature.book_details.presentation.viewModel.BookDetailsViewModelFactory


class AudioBookDetailsFragment : BaseContainerFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dataBinding : FragmentAudiobookDetailsBinding = inflateLayout(inflater,R.layout.fragment_audiobook_details,container)

        val bookId = arguments?.getString("bookId")?:""


        /**
        Lazily initialize the view model
         */
        val bookDetailsViewModel: BookDetailsViewModel by lazy {

            val activity = requireNotNull(this.activity) {
                "You can only access the booksViewModel after onCreated()"
            }

            ViewModelProviders.of(this, BookDetailsViewModelFactory(activity.application,bookId))
                .get(BookDetailsViewModel::class.java)
        }

        dataBinding.lifecycleOwner = viewLifecycleOwner
        dataBinding.audioBookDetailsViewModel = bookDetailsViewModel

        bookDetailsViewModel.backArrowPressed.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                this.findNavController()
                    .navigate(R.id.action_AudioBookDetailsFragment_to_AudioBookListFragment)
            }
        })

        bookDetailsViewModel.playItemClicked.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {bookId ->
                Toast.makeText(context,bookId,Toast.LENGTH_SHORT).show()
            }
        })


        return dataBinding.root
    }
}