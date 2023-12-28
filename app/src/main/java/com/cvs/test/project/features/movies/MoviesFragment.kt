package com.cvs.test.project.features.movies

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cvs.test.project.R
import com.cvs.test.project.databinding.FragmentMoviesBinding
import com.cvs.test.project.features.detail.DetailMovieFragment
import com.cvs.test.project.features.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.zip.Inflater

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    companion object {
        const val SORT_TYPE_TITLE = "title"
        const val SORT_TYPE_DATA = "data"
        const val BUNDLE_MOVIE_TITLE = "movie"

    }

    private val viewBinding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
    private val sharedViewModel by activityViewModel<SharedViewModel>()
    private lateinit var adapter: MoviesAdapter

    override fun onResume() {
        super.onResume()
        sharedViewModel.getListMovies()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter(
            onItemClick = { title ->
                val bundle = Bundle()
                bundle.putString(BUNDLE_MOVIE_TITLE, title)
                findNavController().navigate(R.id.actionOpenDetailMovieFragment, bundle)
            }
        )
        viewBinding.moviesRV.adapter = adapter
        sharedViewModel.getListMovies()
        viewBinding.moviesSortBtn.setOnClickListener {
            showDialog()
        }
        observerListMovies()
    }

    private fun observerListMovies() {
        sharedViewModel.listMovies.observe(viewLifecycleOwner) { movies ->
            adapter.setList(movies)
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val dialogView = layoutInflater.inflate(R.layout.dialog_sort, null)
        builder.setView(dialogView)

        val alertDialog = builder.create()
        alertDialog.show()

        dialogView.findViewById<TextView>(R.id.dialogTitleSortBtn).setOnClickListener {
            sharedViewModel.sortList(SORT_TYPE_TITLE)
            alertDialog.dismiss()
        }
        dialogView.findViewById<TextView>(R.id.dialogDataSortBtn).setOnClickListener {
            sharedViewModel.sortList(SORT_TYPE_DATA)
            alertDialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.dialogSortCloseBtn).setOnClickListener {
            alertDialog.dismiss()
        }

    }
}