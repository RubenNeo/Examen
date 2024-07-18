package com.example.films.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.films.R
import com.example.films.databinding.ActivityDetailsBinding
import com.example.omdb.data.Movie
import com.example.omdb.network.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Details_activity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    lateinit var movie:Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val FilmID = intent.getStringExtra("Film_ID")


        searchFilm(FilmID!!)
    }
    fun searchFilm(query : String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitProvider.apiService.getMovieDetails(query)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    Log.i("Neo", "Funciona")
                    movie = response.body()!!
                    detailsFilm()

                }else{
                    Log.i("Neo", "No funciona")
                }
            }

        }


    }

    private fun detailsFilm() {
        Picasso.get().load(movie.poster).into(binding.ivFilm)
        binding.FilmName.text = movie.title
        binding.yearFilm.text = movie.year
        binding.tvDirector.text = movie.director
        binding.sinopsis.text = movie.plot



    }
}