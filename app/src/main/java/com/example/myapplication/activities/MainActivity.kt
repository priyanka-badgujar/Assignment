package com.example.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.MovieData
import com.example.myapplication.movieadapter.MoviesAdapter
import com.example.myapplication.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var dataBinding: ActivityMainBinding
    private var adapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        //observe movies data
        viewModel.getMovies().observe(this, {
            if (!it?.results.isNullOrEmpty()) {
                setMoviesData(it?.results)
            }
        })
    }

    //Set recyclerview to show list of movies
    private fun setMoviesData(movieResponse: List<MovieData>?) {
        adapter = MoviesAdapter(movieResponse)
        dataBinding.moviesRecyclerView.setHasFixedSize(true)
        dataBinding.moviesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dataBinding.moviesRecyclerView.isNestedScrollingEnabled = false
        dataBinding.moviesRecyclerView.adapter = adapter
    }
}