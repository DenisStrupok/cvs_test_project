package com.cvs.test.project.features.movies

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cvs.test.project.R
import com.cvs.test.project.databinding.FragmentMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.zip.Inflater

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    companion object {
        const val SORT_TYPE_TITLE = "title"
        const val SORT_TYPE_DATA = "data"
    }

    private val viewBinding: FragmentMoviesBinding by viewBinding(FragmentMoviesBinding::bind)
    private val viewModel: MoviesVM by viewModel()
    private lateinit var adapter: MoviesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter(
            onItemClick = { movie ->
                Toast.makeText(requireContext(), "${movie.title}", Toast.LENGTH_SHORT).show()
            }
        )
        viewBinding.moviesRV.adapter = adapter
        viewModel.getListMovies()
        viewBinding.moviesSortBtn.setOnClickListener {
            showDialog()
        }
        observerListMovies()
    }

    private fun observerListMovies() {
        viewModel.listMovies.observe(viewLifecycleOwner) { movies ->
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
            viewModel.sortList(SORT_TYPE_TITLE)
            alertDialog.dismiss()
        }
        dialogView.findViewById<TextView>(R.id.dialogDataSortBtn).setOnClickListener {
            viewModel.sortList(SORT_TYPE_DATA)
            alertDialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.dialogSortCloseBtn).setOnClickListener {
            alertDialog.dismiss()
        }

    }
}