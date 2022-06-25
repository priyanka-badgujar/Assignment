package com.example.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.constant.ApplicationConstants
import com.example.myapplication.api.ApiAdapter
import com.example.myapplication.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object MoviesRepository {

    fun getMoviesData() : LiveData<MoviesResponse?> {
        val moviesResponse= MutableLiveData<MoviesResponse>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiAdapter.apiClient.getMoviesData(
                    ApplicationConstants.APIKEY_PARAM,
                    ApplicationConstants.LANG_PARAM,
                    ApplicationConstants.PAGE_PARAM
                )
                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main) {
                        moviesResponse.value = response.body()!!
                    }
                }
            } catch (e: Exception) {
                Log.i("Response error", e.toString())
            }
        }
        return moviesResponse
    }
}