package com.cvs.test.project.features.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cvs.test.project.databinding.ItemMovieBinding
import com.cvs.test.project.models.MovieModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class MoviesAdapter(
    private val onItemClick: (MovieModel) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var items = mutableListOf<MovieModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: MutableList<MovieModel>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val item: ItemMovieBinding) :
        RecyclerView.ViewHolder(item.root) {
        fun bind(movie: MovieModel) {
            val inputFormat = SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH)
            val date: Date = inputFormat.parse(movie.releasedData) ?: Date()
            val outputFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
            val year = outputFormat.format(date)

            Picasso.get().load(movie.poster).into(item.itemMovieImg)
            item.itemMovieTitleTv.text = "${movie.title}($year)"
            item.itemMovieSubTitle.text = "${movie.duration} - ${movie.genre}"
            item.itemMovieContainer.setOnClickListener {
                onItemClick.invoke(movie)
            }
        }
    }
}