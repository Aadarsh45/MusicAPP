package com.example.musicapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import org.w3c.dom.Text
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView= findViewById(R.id.recyclerView)
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")

        retrofitData.enqueue(object :Callback<MyData?>{
            override fun onResponse(call: Call<MyData?>,response: Response<MyData?>) {
                val dataList = response.body()?.data!!
                myAdapter = MyAdapter(this@MainActivity,dataList)

                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)

//                var tv = findViewById<TextView>(R.id.textView)
//                tv.text = response.body()?.data.toString()
            }

            override fun onFailure(call: Call<MyData?>,t:Throwable){
               Log.d("MainActivity","onFailure: "+t.message)
            }
        })
    }
}