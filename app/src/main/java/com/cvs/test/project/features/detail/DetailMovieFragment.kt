package com.cvs.test.project.features.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cvs.test.project.R
import com.cvs.test.project.databinding.FragmentDetailMovieBinding
import com.cvs.test.project.features.movies.MoviesFragment.Companion.BUNDLE_MOVIE_TITLE
import com.cvs.test.project.features.viewmodels.SharedViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private val viewBinding: FragmentDetailMovieBinding by viewBinding(FragmentDetailMovieBinding::bind)
    private val sharedViewModel by activityViewModel<SharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(BUNDLE_MOVIE_TITLE)?.let { title ->
            sharedViewModel.getMovie(title)
        }

        viewBinding.detailMovieBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        viewBinding.detailAddToListBtn.setOnClickListener {
            sharedViewModel.addToList()
        }
        viewBinding.detailRemoveFromListBtn.setOnClickListener {
            sharedViewModel.removeFromList()
        }
        observerMovie()
        observerIsAdded()
    }

    private fun observerMovie() {
        sharedViewModel.movie.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                Picasso.get().load(movie.poster).into(viewBinding.detailMovieImg)
                viewBinding.detailMovieTitle.text = movie.title
                viewBinding.detailMovieDescription.text = movie.description
                viewBinding.detailMovieGenreResult.text = movie.genre
                viewBinding.detailMovieReleasedResult.text = movie.releasedData
                viewBinding.detailMovieRatingResult.text =
                    getString(R.string.rating_result, movie.rating)
            }
        }
    }

    private fun observerIsAdded() {
        sharedViewModel.isAdd.observe(viewLifecycleOwner) { isAdded ->
            if (isAdded) {
                viewBinding.detailAddToListBtn.visibility = View.GONE
                viewBinding.detailRemoveFromListBtn.visibility = View.VISIBLE
            } else {
                viewBinding.detailAddToListBtn.visibility = View.VISIBLE
                viewBinding.detailRemoveFromListBtn.visibility = View.GONE
            }
        }
    }
}