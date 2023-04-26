package com.example.appconsumoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class JokeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)

        val myButtonJoke=findViewById<Button>(R.id.btnJoke)


        myButtonJoke.setOnClickListener {
            loadJoke()
        }
    }

    private fun loadJoke() {
        val myTextBroma=findViewById<TextView>(R.id.tvBroma)


        val retrofit= Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //myTextBroma.text="Broma encontrada!!!!"
        val jokeService:JokeService
        jokeService=retrofit.create(JokeService::class.java)

        val request = jokeService.getJoke("json")

        request.enqueue(object : Callback<Joke>{
            override fun onFailure(call:Call<Joke>,t:Throwable){
                Log.d("jokeActivity",t.toString())
            }
            override fun onResponse(call:Call<Joke>,response: Response<Joke>){
                if(response.isSuccessful){
                    myTextBroma.text=response.body()!!.joke
                }
            }
        }

        )


    }
}