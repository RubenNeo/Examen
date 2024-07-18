package com.example.films.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.Adapter.FilmsAdapter
import com.example.films.databinding.ActivityMainBinding
import com.example.omdb.data.Movie
import com.example.omdb.network.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FilmsAdapter
    var dataset: List<Movie> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        searchFilm("Matrix")

        adapter = FilmsAdapter(onItemSelected = { Filmid ->
            navigateToDetails(Filmid)
        })
        binding.filmsRV.adapter = adapter
        binding.filmsRV.layoutManager = LinearLayoutManager(this)
    }

    private fun navigateToDetails(id: String) {
        val intent = Intent(this, Details_activity::class.java)
        intent.putExtra("Film_ID",id)
        startActivity(intent)
    }
    fun searchFilm(query : String){
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitProvider.apiService.searchByText(query)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    Log.i("Neo", "Funciona")
                    dataset = response.body()?.FindAll!!
                    adapter .updateDataSet(dataset)

                }else{
                    Log.i("Neo", "No funciona")
                }
            }

        }


    }
}

