package com.example.films.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.films.databinding.FilmsLayoutBinding
import com.example.omdb.data.Movie
import com.squareup.picasso.Picasso

class FilmsAdapter(
    private var dataset: List<Movie> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<FilmsAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: FilmsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie, onItemSelected: (String) -> Unit) {
            binding.nameFilm.text = movie.title
            binding.yearFilm.text = movie.year
            Picasso.get().load(movie.poster).into(binding.filmIV)

            binding.root.setOnClickListener {
                onItemSelected(movie.imdbID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = FilmsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(dataset[position], onItemSelected)
    }

    fun updateDataSet(dataset: List<Movie>) {
        this.dataset = dataset
        notifyDataSetChanged()

    }

}