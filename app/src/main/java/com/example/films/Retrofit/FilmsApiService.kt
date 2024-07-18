package com.example.films.Retrofit

import androidx.appcompat.widget.SearchView
import com.example.omdb.data.Movie
import com.example.omdb.data.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val OMBD_API_KEY = "d356f65a"
interface OMBdiApiService {
        @GET("/")
        suspend fun searchByText(@Query("s") query:String, @Query("apikey") apiKey:String = OMBD_API_KEY): Response<Search>

        @GET("/")
        suspend fun getMovieDetails(@Query("i") query:String, @Query("apikey") apiKey:String = OMBD_API_KEY): Response<Movie>
    }
