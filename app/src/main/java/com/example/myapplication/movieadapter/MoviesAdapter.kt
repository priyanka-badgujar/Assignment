package com.example.myapplication.movieadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.constant.ApplicationConstants
import com.example.myapplication.R
import com.example.myapplication.databinding.RecyclerviewMoviesBinding
import com.example.myapplication.model.MovieData

class MoviesAdapter(
    private val data: List<MovieData>?
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_movies,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieData = data?.get(position)
        holder.dataBinding.moviesTitle.text = movieData?.title
        holder.dataBinding.movieDescription.text = movieData?.overview
        val posterPath = ApplicationConstants.POSTER_BASE_URL + movieData?.poster_path
        holder.dataBinding.moviesPoster.load(posterPath)
    }

    data class ViewHolder(var dataBinding: RecyclerviewMoviesBinding) :
        RecyclerView.ViewHolder(dataBinding.root)
}