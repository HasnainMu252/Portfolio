package com.example.portfolio_mhm

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var recyclerRepo: RecyclerView
        lateinit var adapter: repo_adapter

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        recyclerRepo = findViewById(R.id.Repolist)
        recyclerRepo.layoutManager = LinearLayoutManager(this)




            val service = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api_interface::class.java)

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val token = "" // enter your token from GitHu and paste here
                    val repositories = service.getRepositories(token)

                    withContext(Dispatchers.Main) {
                        if (repositories.isNotEmpty()) {
                            adapter = repo_adapter(this@MainActivity, repositories)
                            recyclerRepo.adapter = adapter
                            Log.d("Repository List", repositories.toString())

                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "No repositories found",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("Repository List", repositories.toString())

                        }
                    }
                } catch (e: HttpException) {
                    withContext(Dispatchers.Main) {
                        if (e.code() == 401) {
                            Toast.makeText(
                                this@MainActivity,
                                "Unauthorized: Invalid Token",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Log.e("Error", e.toString())
                            Toast.makeText(
                                this@MainActivity,
                                "Error: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }


    }




