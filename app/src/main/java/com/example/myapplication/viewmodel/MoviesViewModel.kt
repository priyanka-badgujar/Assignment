package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.MoviesResponse
import com.example.myapplication.repository.MoviesRepository

class MoviesViewModel: ViewModel() {

    fun getMovies() : LiveData<MoviesResponse?> {
        return MoviesRepository.getMoviesData()
    }
}